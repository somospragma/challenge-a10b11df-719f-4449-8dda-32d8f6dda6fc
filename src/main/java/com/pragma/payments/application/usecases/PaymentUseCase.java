package com.pragma.payments.application.usecases;


import com.pragma.payments.domain.model.TransactionStatus;
import com.pragma.payments.application.ports.in.PaymentServicePort;
import com.pragma.payments.application.ports.out.FraudDetectionPort;
import com.pragma.payments.application.ports.out.RiskBureauPort;
import com.pragma.payments.domain.exception.TransactionAlreadyProcessedException;
import com.pragma.payments.domain.exception.ExternalServiceTimeoutException;
import com.pragma.payments.domain.model.IdempotencyKey;
import com.pragma.payments.domain.model.Transaction;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PaymentUseCase implements PaymentServicePort {

    private static final Logger log = LoggerFactory.getLogger(PaymentUseCase.class);
    private static final Duration EXTERNAL_SERVICE_TIMEOUT = Duration.ofSeconds(3);
    private static final int MAX_RETRY_ATTEMPTS = 3;

    private final FraudDetectionPort fraudDetectionPort;
    private final RiskBureauPort riskBureauPort;
    private final java.util.Map<String, Transaction> transactionCache;

    public PaymentUseCase(
            FraudDetectionPort fraudDetectionPort,
            RiskBureauPort riskBureauPort) {
        this.fraudDetectionPort = fraudDetectionPort;
        this.riskBureauPort = riskBureauPort;
        this.transactionCache = new java.util.concurrent.ConcurrentHashMap<>();
    }

    @Override
    @CircuitBreaker(name = "paymentCircuitBreaker", fallbackMethod = "processPaymentFallback")
    @Retry(name = "paymentRetry", maxAttempts = MAX_RETRY_ATTEMPTS)
    public Mono<Transaction> processPayment(Mono<Transaction> transactionMono) {
        return transactionMono
                .flatMap(this::validateIdempotency)
                .flatMap(this::executeFraudDetection)
                .flatMap(this::executeRiskEvaluation)
                .flatMap(this::finalizeTransaction)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Boolean> isTransactionProcessed(IdempotencyKey idempotencyKey) {
        String cacheKey = buildCacheKey(idempotencyKey);
        return Mono.fromCallable(() -> transactionCache.containsKey(cacheKey))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Transaction> getProcessedTransaction(IdempotencyKey idempotencyKey) {
        String cacheKey = buildCacheKey(idempotencyKey);
        return Mono.fromCallable(() -> transactionCache.get(cacheKey))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(transaction -> transaction != null
                        ? Mono.just(transaction)
                        : Mono.empty());
    }

    private Mono<Transaction> validateIdempotency(Transaction transaction) {
        IdempotencyKey key = transaction.getIdempotencyKey();
        String cacheKey = buildCacheKey(key);

        return Mono.fromCallable(() -> transactionCache.get(cacheKey))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(existing -> {
                    if (existing != null) {
                        log.warn("Transacción duplicada detectada para clave: {}", cacheKey);
                        return Mono.error(new TransactionAlreadyProcessedException(
                                "Transacción ya procesada para la clave de idempotencia: " + key.getOperationNumber()));
                    }
                    return Mono.just(transaction);
                });
    }

    @CircuitBreaker(name = "fraudDetectionCircuitBreaker", fallbackMethod = "fraudDetectionFallback")
    private Mono<Transaction> executeFraudDetection(Transaction transaction) {
        return fraudDetectionPort.evaluateTransaction(transaction)
                .timeout(EXTERNAL_SERVICE_TIMEOUT)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(isFraudulent -> {
                    if (Boolean.TRUE.equals(isFraudulent)) {
                        log.warn("Transacción {} rechazada por motor antifraude", transaction.getId());
                        transaction.setStatus(Transaction.TransactionStatus.REJECTED);
                        transaction.setFailureReason("Rechazada por motor antifraude");
                    } else {
                        log.info("Transacción {} aprobada por motor antifraude", transaction.getId());
                    }
                    return Mono.just(transaction);
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error en detección de fraude para transacción {}: {}",
                            transaction.getId(), e.getMessage());
                    return Mono.error(new ExternalServiceTimeoutException(
                            "Timeout en servicio de detección de fraude"));
                });
    }

    @CircuitBreaker(name = "riskBureauCircuitBreaker", fallbackMethod = "riskEvaluationFallback")
    private Mono<Transaction> executeRiskEvaluation(Transaction transaction) {
        if (transaction.getStatus() == Transaction.TransactionStatus.REJECTED) {
            return Mono.just(transaction);
        }

        return riskBureauPort.getRiskScore(transaction)
                .timeout(EXTERNAL_SERVICE_TIMEOUT)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(score -> {
                    transaction.setRiskScore(score);
                    log.info("Puntuación de riesgo para transacción {}: {}", transaction.getId(), score);

                    if (score >= 70) {
                        transaction.setStatus(Transaction.TransactionStatus.REJECTED);
                        transaction.setFailureReason("Riesgo alto detectado: puntuación " + score);
                        log.warn("Transacción {} rechazada por riesgo alto: {}", transaction.getId(), score);
                    }
                    return Mono.just(transaction);
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error en evaluación de riesgo para transacción {}: {}",
                            transaction.getId(), e.getMessage());
                    return Mono.error(new ExternalServiceTimeoutException(
                            "Timeout en servicio de buró de riesgos"));
                });
    }

    private Mono<Transaction> finalizeTransaction(Transaction transaction) {
        return Mono.fromCallable(() -> {
            if (transaction.getStatus() != Transaction.TransactionStatus.REJECTED) {
                transaction.setStatus(Transaction.TransactionStatus.APPROVED);
            }
            transaction.setProcessedAt(Instant.now());

            String cacheKey = buildCacheKey(transaction.getIdempotencyKey());
            transactionCache.put(cacheKey, transaction);

            log.info("Transacción {} finalizada con estado: {}",
                    transaction.getId(), transaction.getStatus());
            return transaction;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private String buildCacheKey(IdempotencyKey key) {
        return key.getOperationNumber() + "_" + key.getChannel();
    }

    private Mono<Transaction> processPaymentFallback(Transaction transaction, Throwable t) {
        log.error("Circuit breaker activado para transacción {}. Fallback ejecutado. Error: {}",
                transaction.getId(), t.getMessage());

        transaction.setStatus(Transaction.TransactionStatus.PENDING);
        transaction.setFailureReason("Servicio temporalmente no disponible. Por favor intente más tarde.");
        return Mono.just(transaction);
    }

    private Mono<Transaction> fraudDetectionFallback(Transaction transaction, Throwable t) {
        log.warn("Fallback de detección de fraude para transacción {}. Error: {}",
                transaction.getId(), t.getMessage());
        return Mono.just(transaction);
    }

    private Mono<Transaction> riskEvaluationFallback(Transaction transaction, Throwable t) {
        log.warn("Fallback de evaluación de riesgo para transacción {}. Error: {}",
                transaction.getId(), t.getMessage());
        return Mono.just(transaction);
    }
}