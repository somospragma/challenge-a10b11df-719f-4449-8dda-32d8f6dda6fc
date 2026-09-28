package com.pragma.payments.infrastructure.adapters.out.fraud;

import com.pragma.payments.application.ports.out.FraudDetectionPort;
import com.pragma.payments.domain.model.Transaction;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FraudDetectionAdapter implements FraudDetectionPort {

    private static final Logger log = LoggerFactory.getLogger(FraudDetectionAdapter.class);
    private static final String FRAUD_SERVICE_URL = "http://fraud-detection-service:8081/api/v1/evaluate";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(2);

    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;
    private final Map<String, Boolean> fraudCache;

    public FraudDetectionAdapter(
            WebClient webClient,
            @Qualifier("fraudDetectionCircuitBreaker") CircuitBreakerRegistry circuitBreakerRegistry) {
        this.webClient = webClient;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("fraudDetection");
        this.fraudCache = new ConcurrentHashMap<>();
    }

    @Override
    public Mono<Boolean> evaluateTransaction(Transaction transaction) {
        String cacheKey = buildCacheKey(transaction);

        if (fraudCache.containsKey(cacheKey)) {
            log.debug("Evaluación de fraude cacheada para transacción: {}", transaction.getId());
            return Mono.just(fraudCache.get(cacheKey)).subscribeOn(Schedulers.boundedElastic());
        }

        return Mono.fromCallable(() -> buildFraudRequest(transaction))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(requestBody -> webClient.post()
                        .uri(FRAUD_SERVICE_URL)
                        .bodyValue(requestBody)
                        .retrieve()
                        .onStatus(HttpStatusCode::is5xxServerError,
                                response -> Mono.error(new RuntimeException(
                                        "Error del servicio de detección de fraude: " + response.statusCode())))
                        .bodyToMono(FraudEvaluationResponse.class)
                        .timeout(REQUEST_TIMEOUT))
                .doOnSuccess(response -> {
                    log.info("Evaluación de fraude completada para transacción {}: isFraudulent={}",
                            transaction.getId(), response.isFraudulent());
                    fraudCache.put(cacheKey, response.isFraudulent());
                })
                .doOnError(error -> log.error("Error en evaluación de fraude para transacción {}: {}",
                        transaction.getId(), error.getMessage()))
                .map(FraudEvaluationResponse::isFraudulent)
                .onErrorResume(WebClientResponseException.ServiceUnavailable.class, e -> {
                    log.warn("Servicio de fraude no disponible, aplicando lógica de falla segura");
                    return Mono.just(false);
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error inesperado en evaluación de fraude: {}", e.getMessage());
                    return Mono.just(true);
                });
    }

    private Map<String, Object> buildFraudRequest(Transaction transaction) {
        return Map.of(
                "transactionId", transaction.getId(),
                "accountId", transaction.getAccountId(),
                "amount", transaction.getAmount(),
                "currency", transaction.getCurrency(),
                "channel", transaction.getIdempotencyKey().getChannel(),
                "description", transaction.getDescription() != null ? transaction.getDescription() : ""
        );
    }

    private String buildCacheKey(Transaction transaction) {
        return transaction.getAccountId() + "_" + transaction.getAmount() + "_" +
                System.currentTimeMillis() / (60 * 1000);
    }

    public void clearCache() {
        fraudCache.clear();
        log.info("Caché de detección de fraude limpiada");
    }

    public int getCacheSize() {
        return fraudCache.size();
    }

    private record FraudEvaluationResponse(boolean isFraudulent, String riskLevel, String message) {}
}