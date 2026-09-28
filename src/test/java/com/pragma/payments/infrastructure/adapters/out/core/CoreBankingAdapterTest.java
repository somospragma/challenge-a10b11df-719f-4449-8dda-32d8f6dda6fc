package com.pragma.payments.infrastructure.adapters.out.core;

import com.pragma.payments.domain.exception.ExternalServiceTimeoutException;
import com.pragma.payments.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CoreBankingAdapter - Tests de integración con el Core Bancario")
class CoreBankingAdapterTest {

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private CoreBankingAdapter adapter;

    private Transaction createTestTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .accountId("ACC-12345")
                .amount(new BigDecimal("1000.00"))
                .channel("WEB")
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @BeforeEach
    void setUp() {
        adapter = new CoreBankingAdapter("http://core-banking-service:8080");
    }

    @Nested
    @DisplayName("Escenarios de Éxito")
    class SuccessScenarios {

        @Test
        @DisplayName("Debe procesar exitosamente una transacción en el core bancario")
        void shouldProcessTransactionSuccessfully() {
            Transaction transaction = createTestTransaction();
            String coreResponse = "{\"transactionId\":\"CORE-98765\",\"status\":\"APPROVED\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(coreResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectNextMatches(result -> 
                        result.contains("CORE-98765") && result.contains("APPROVED"))
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe retornar APPROVED cuando el core aprueba la transacción")
        void shouldReturnApprovedStatus() {
            Transaction transaction = createTestTransaction();
            String coreResponse = "{\"status\":\"APPROVED\",\"authorizationCode\":\"AUTH-001\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(coreResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectNextMatches(response -> response.contains("APPROVED"))
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallo")
    class FailureScenarios {

        @Test
        @DisplayName("Debe manejar respuesta 500 del core bancario")
        void shouldHandle500ErrorFromCore() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.INTERNAL_SERVER_ERROR),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Core Banking unavailable")));

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar respuesta 503 del core bancario")
        void shouldHandle503ErrorFromCore() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.SERVICE_UNAVAILABLE),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Service temporarily unavailable")));

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar respuesta 502 Bad Gateway")
        void shouldHandle502BadGateway() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.BAD_GATEWAY),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Bad Gateway")));

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar error de conexión con el core")
        void shouldHandleConnectionFailure() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(any(), any()))
                .thenReturn(Mono.error(new RuntimeException("Connection refused")));

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }
    }

    @Nested
    @DisplayName("Escenarios de Timeout")
    class TimeoutScenarios {

        @Test
        @DisplayName("Debe lanzar excepción cuando el core no responde a tiempo")
        void shouldThrowTimeoutExceptionWhenCoreDoesNotRespond() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(any(), any()))
                .thenReturn(responseSpec);
            when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.delay(java.time.Duration.ofSeconds(30))
                        .flatMap(ignored -> Mono.empty()));

            StepVerifier.create(adapter.processTransaction(transaction)
                    .timeout(java.time.Duration.ofSeconds(5)))
                    .expectError(ExternalServiceTimeoutException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar timeout en la llamada al core bancario")
        void shouldHandleTimeoutGracefully() {
            Transaction transaction = createTestTransaction();

            StepVerifier.create(adapter.processTransaction(transaction)
                    .timeout(java.time.Duration.ofMillis(100))
                    .onErrorResume(ExternalServiceTimeoutException.class, e -> Mono.just("TIMEOUT")))
                    .expectNext("TIMEOUT")
                    .verifyComplete();
        }
    }
}