package com.pragma.payments.infrastructure.adapters.out.core;

import com.pragma.payments.application.ports.out.CoreBankingPort;
import com.pragma.payments.domain.exception.ExternalServiceTimeoutException;
import com.pragma.payments.domain.model.Transaction;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.operator.RetryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;

@Component
public class CoreBankingAdapter implements CoreBankingPort {
    
    private static final Logger log = LoggerFactory.getLogger(CoreBankingAdapter.class);
    private static final String CIRCUIT_BREAKER_NAME = "coreBankingCircuitBreaker";
    private static final String RETRY_NAME = "coreBankingRetry";
    
    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;
    private final Retry retry;
    private final String baseUrl;
    private final Duration defaultTimeout;
    
    public CoreBankingAdapter(
            WebClient webClient,
            @Value("${app.services.core-banking.base-url:http://localhost:8081}") String baseUrl,
            @Value("${app.services.core-banking.timeout-ms:5000}") long timeoutMs,
            CircuitBreakerRegistry circuitBreakerRegistry,
            RetryRegistry retryRegistry) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
        this.defaultTimeout = Duration.ofMillis(timeoutMs);
        
        CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME, cbConfig);
        
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(2))
                .retryExceptions(ExternalServiceTimeoutException.class, 
                               WebClientResponseException.ServiceUnavailable.class)
                .ignoreExceptions(WebClientResponseException.BadRequest.class)
                .build();
        this.retry = retryRegistry.retry(RETRY_NAME, retryConfig);
        
        log.info("CoreBankingAdapter initialized with baseUrl: {}, timeout: {}ms", 
                baseUrl, timeoutMs);
    }
    
    @Override
    public Mono<Boolean> authorizeTransaction(Transaction transaction) {
        log.info("Authorizing transaction {} with core banking", transaction.getId());
        
        return webClient.post()
                .uri(baseUrl + "/api/v1/transactions/authorize")
                .bodyValue(buildAuthorizationRequest(transaction))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, handle5xxError())
                .bodyToMono(AuthorizationResponse.class)
                .timeout(defaultTimeout, Mono.error(
                    new ExternalServiceTimeoutException("CORE_BANKING", "/authorize", 
                        defaultTimeout, defaultTimeout)))
                .transformDeferred(circuitBreakerOperator())
                .transformDeferred(retryOperator())
                .doOnSuccess(response -> log.info("Transaction {} authorized: {}", 
                    transaction.getId(), response.authorized()))
                .doOnError(error -> log.error("Failed to authorize transaction {}: {}", 
                    transaction.getId(), error.getMessage()))
                .map(AuthorizationResponse::authorized)
                .onErrorResume(handleError(transaction));
    }
    
    @Override
    public Mono<Boolean> settleTransaction(Transaction transaction) {
        log.info("Settling transaction {} with core banking", transaction.getId());
        
        return webClient.post()
                .uri(baseUrl + "/api/v1/transactions/settle")
                .bodyValue(buildSettlementRequest(transaction))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, handle5xxError())
                .bodyToMono(SettlementResponse.class)
                .timeout(defaultTimeout, Mono.error(
                    new ExternalServiceTimeoutException("CORE_BANKING", "/settle", 
                        defaultTimeout, defaultTimeout)))
                .transformDeferred(circuitBreakerOperator())
                .transformDeferred(retryOperator())
                .doOnSuccess(response -> log.info("Transaction {} settled: {}", 
                    transaction.getId(), response.settled()))
                .doOnError(error -> log.error("Failed to settle transaction {}: {}", 
                    transaction.getId(), error.getMessage()))
                .map(SettlementResponse::settled)
                .onErrorResume(handleError(transaction));
    }
    
    @Override
    public Mono<Boolean> reverseTransaction(String transactionId, String reason) {
        log.info("Reversing transaction {} with reason: {}", transactionId, reason);
        
        return webClient.post()
                .uri(baseUrl + "/api/v1/transactions/{id}/reverse", transactionId)
                .bodyValue(Map.of("reason", reason))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, handle5xxError())
                .bodyToMono(ReverseResponse.class)
                .timeout(defaultTimeout, Mono.error(
                    new ExternalServiceTimeoutException("CORE_BANKING", "/reverse", 
                        defaultTimeout, defaultTimeout)))
                .transformDeferred(circuitBreakerOperator())
                .transformDeferred(retryOperator())
                .doOnSuccess(response -> log.info("Transaction {} reversed: {}", 
                    transactionId, response.reversed()))
                .doOnError(error -> log.error("Failed to reverse transaction {}: {}", 
                    transactionId, error.getMessage()))
                .map(ReverseResponse::reversed)
                .onErrorResume(e -> {
                    log.error("Error reversing transaction {}: {}", transactionId, e.getMessage());
                    return Mono.just(false);
                });
    }
    
    private Map<String, Object> buildAuthorizationRequest(Transaction transaction) {
        return Map.of(
            "transactionId", transaction.getId(),
            "amount", transaction.getAmount().toString(),
            "currency", transaction.getCurrency(),
            "sourceAccount", transaction.getSourceAccount(),
            "destinationAccount", transaction.getDestinationAccount(),
            "channel", transaction.getChannel()
        );
    }
    
    private Map<String, Object> buildSettlementRequest(Transaction transaction) {
        return Map.of(
            "transactionId", transaction.getId(),
            "settlementDate", java.time.Instant.now().toString()
        );
    }
    
    private Function<org.springframework.web.reactive.function.client.ClientResponse, 
                      Mono<? extends Throwable>> handle5xxError() {
        return response -> {
            log.error("Core banking returned 5xx error: {}", response.statusCode());
            return Mono.error(new WebClientResponseException(
                "Core banking service unavailable",
                response.statusCode().value(),
                "Service temporarily unavailable",
                null,
                null));
        };
    }
    
    private <T> reactor.core.publisher.Flux<T> circuitBreakerOperator() {
        return source -> source
            .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
            .doOnCallSuccess(result -> log.debug("Circuit breaker call succeeded"))
            .doOnCallFailure(throwable -> log.warn("Circuit breaker call failed: {}", 
                throwable.getMessage()));
    }
    
    private <T> reactor.core.publisher.Flux<T> retryOperator() {
        return source -> source
            .transformDeferred(RetryOperator.of(retry))
            .doOnRetry(signal -> log.info("Retry attempt {} for core banking call", 
                signal.totalRetries() + 1));
    }
    
    private Function<Throwable, Mono<Boolean>> handleError(Transaction transaction) {
        return throwable -> {
            if (throwable instanceof ExternalServiceTimeoutException) {
                log.warn("Timeout processing transaction {}, returning false", transaction.getId());
                return Mono.just(false);
            }
            if (throwable instanceof io.github.resilience4j.circuitbreaker.CallNotPermittedException) {
                log.error("Circuit breaker open for transaction {}, returning false", 
                    transaction.getId());
                return Mono.just(false);
            }
            log.error("Unexpected error processing transaction {}, returning false: {}", 
                transaction.getId(), throwable.getMessage());
            return Mono.just(false);
        };
    }
    
    public CircuitBreaker getCircuitBreaker() {
        return circuitBreaker;
    }
    
    public Retry getRetry() {
        return retry;
    }
    
    public record AuthorizationResponse(String transactionId, boolean authorized, String authorizationCode) {}
    public record SettlementResponse(String transactionId, boolean settled, String settlementId) {}
    public record ReverseResponse(String transactionId, boolean reversed, String reversalId) {}
}