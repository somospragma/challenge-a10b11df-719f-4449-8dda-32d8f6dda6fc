package com.pragma.payments.infrastructure.adapters.out.fraud;

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
@DisplayName("FraudDetectionAdapter - Tests del adaptador del motor antifraude")
class FraudDetectionAdapterTest {

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private ClientResponse clientResponse;

    private FraudDetectionAdapter adapter;

    private Transaction createTestTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .accountId("ACC-12345")
                .amount(new BigDecimal("5000.00"))
                .channel("MOBILE")
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @BeforeEach
    void setUp() {
        adapter = new FraudDetectionAdapter("http://fraud-detection-service:8080");
    }

    @Nested
    @DisplayName("Escenarios de Éxito")
    class SuccessScenarios {

        @Test
        @DisplayName("Debe aprobar transacción legítima")
        void shouldApproveLegitimateTransaction() {
            Transaction transaction = createTestTransaction();
            String fraudResponse = "{\"fraudDetected\":false,\"riskLevel\":\"LOW\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(true)
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe detectar fraude en transacción de alto riesgo")
        void shouldDetectFraudInHighRiskTransaction() {
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .accountId("ACC-SUSPICIOUS")
                    .amount(new BigDecimal("50000.00"))
                    .channel("API")
                    .status("PENDING")
                    .createdAt(LocalDateTime.now())
                    .build();

            String fraudResponse = "{\"fraudDetected\":true,\"riskLevel\":\"HIGH\",\"reason\":\"unusual_amount\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(false)
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe evaluar correctamente transacciones con monto bajo")
        void shouldApproveLowAmountTransactions() {
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .accountId("ACC-12345")
                    .amount(new BigDecimal("100.00"))
                    .channel("WEB")
                    .status("PENDING")
                    .createdAt(LocalDateTime.now())
                    .build();

            String fraudResponse = "{\"fraudDetected\":false,\"riskLevel\":\"LOW\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(true)
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallo")
    class FailureScenarios {

        @Test
        @DisplayName("Debe manejar error 500 del servicio antifraude")
        void shouldHandle500Error() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.INTERNAL_SERVER_ERROR),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Fraud service error")));

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar error 503 del servicio antifraude")
        void shouldHandle503Error() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.SERVICE_UNAVAILABLE),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Fraud service unavailable")));

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar error de conexión")
        void shouldHandleConnectionError() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(any(), any()))
                .thenReturn(responseSpec);
            when(responseSpec.bodyToMono(String.class))
                    .thenReturn(Mono.error(new RuntimeException("Connection refused")));

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar respuesta inválida del servicio")
        void shouldHandleInvalidResponse() {
            Transaction transaction = createTestTransaction();
            String invalidResponse = "invalid-json";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(invalidResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectError(Exception.class)
                    .verify();
        }
    }

    @Nested
    @DisplayName("Escenarios de Timeout")
    class TimeoutScenarios {

        @Test
        @DisplayName("Debe manejar timeout del servicio antifraude")
        void shouldHandleServiceTimeout() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(any(), any()))
                .thenReturn(responseSpec);
            when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.delay(java.time.Duration.ofSeconds(30))
                        .flatMap(ignored -> Mono.empty()));

            StepVerifier.create(adapter.evaluateTransaction(transaction)
                    .timeout(java.time.Duration.ofSeconds(2)))
                    .expectError(Exception.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe retornar false por defecto en timeout")
        void shouldReturnFalseOnTimeoutByDefault() {
            Transaction transaction = createTestTransaction();

            StepVerifier.create(adapter.evaluateTransaction(transaction)
                    .timeout(java.time.Duration.ofMillis(100))
                    .onErrorResume(Exception.class, e -> Mono.just(false)))
                    .expectNext(false)
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Validación de Datos")
    class DataValidationScenarios {

        @Test
        @DisplayName("Debe enviar el accountId correcto al servicio")
        void shouldSendCorrectAccountId() {
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .accountId("ACC-SPECIFIC-123")
                    .amount(new BigDecimal("1000.00"))
                    .channel("WEB")
                    .status("PENDING")
                    .createdAt(LocalDateTime.now())
                    .build();

            String fraudResponse = "{\"fraudDetected\":false,\"riskLevel\":\"LOW\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(true)
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe enviar el monto correcto al servicio")
        void shouldSendCorrectAmount() {
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .accountId("ACC-12345")
                    .amount(new BigDecimal("9999.99"))
                    .channel("API")
                    .status("PENDING")
                    .createdAt(LocalDateTime.now())
                    .build();

            String fraudResponse = "{\"fraudDetected\":false,\"riskLevel\":\"MEDIUM\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(true)
                    .verifyComplete();
        }
    }
}