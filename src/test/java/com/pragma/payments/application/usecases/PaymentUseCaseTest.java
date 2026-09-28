package com.pragma.payments.application.usecases;


import com.pragma.payments.infrastructure.adapters.out.core.CoreBankingAdapter;
import com.pragma.payments.application.ports.out.FraudDetectionPort;
import com.pragma.payments.application.ports.out.RiskBureauPort;
import com.pragma.payments.domain.model.IdempotencyKey;
import com.pragma.payments.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentUseCase - Tests de caso de uso de pagos")
class PaymentUseCaseTest {

    @Mock
    private FraudDetectionPort fraudDetectionPort;

    @Mock
    private RiskBureauPort riskBureauPort;

    @Mock
    private com.pragma.payments.infrastructure.adapters.out.core.CoreBankingAdapter coreBankingAdapter;

    private PaymentUseCase paymentUseCase;

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

    private IdempotencyKey createIdempotencyKey(String operationNumber, String channel) {
        return IdempotencyKey.builder()
                .operationNumber(operationNumber)
                .channel(channel)
                .build();
    }

    @BeforeEach
    void setUp() {
        paymentUseCase = new PaymentUseCase(
                fraudDetectionPort,
                riskBureauPort,
                coreBankingAdapter
        );
    }

    @Nested
    @DisplayName("Escenarios de Éxito")
    class SuccessScenarios {

        @Test
        @DisplayName("Debe procesar exitosamente una transacción")
        void shouldProcessTransactionSuccessfully() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .assertNext(result -> {
                        assertThat(result).isNotNull();
                        assertThat(result.getStatus()).isEqualTo("COMPLETED");
                    })
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe aprobar cuando el riesgo es bajo")
        void shouldApproveWhenRiskIsLow() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(5));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .assertNext(result -> assertThat(result.getStatus()).isEqualTo("COMPLETED"))
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallo por Validaciones")
    class ValidationFailureScenarios {

        @Test
        @DisplayName("Debe rechazar cuando el motor antifraude detecta fraude")
        void shouldRejectWhenFraudDetected() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(false));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .expectErrorMatches(e -> e.getMessage().contains("fraud"))
                    .verify();
        }

        @Test
        @DisplayName("Debe rechazar cuando la cuenta está en lista negra")
        void shouldRejectWhenAccountIsBlacklisted() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(true));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .expectErrorMatches(e -> e.getMessage().contains("blacklisted"))
                    .verify();
        }

        @Test
        @DisplayName("Debe rechazar cuando el riesgo es alto")
        void shouldRejectWhenRiskIsHigh() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(85));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .expectErrorMatches(e -> e.getMessage().contains("risk"))
                    .verify();
        }
    }

    @Nested
    @DisplayName("Escenarios de Concurrencia")
    class ConcurrencyScenarios {

        @Test
        @DisplayName("Debe procesar múltiples transacciones concurrentemente")
        void shouldHandleConcurrentTransactions() throws InterruptedException {
            int concurrentRequests = 10;
            CountDownLatch latch = new CountDownLatch(concurrentRequests);
            AtomicInteger successCount = new AtomicInteger(0);

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            ExecutorService executor = Executors.newFixedThreadPool(concurrentRequests);

            for (int i = 0; i < concurrentRequests; i++) {
                final int index = i;
                executor.submit(() -> {
                    try {
                        Transaction tx = Transaction.builder()
                                .id(UUID.randomUUID().toString())
                                .accountId("ACC-" + index)
                                .amount(new BigDecimal("1000.00"))
                                .channel("WEB")
                                .status("PENDING")
                                .createdAt(LocalDateTime.now())
                                .build();

                        paymentUseCase.processPayment(Mono.just(tx))
                                .doOnSuccess(result -> successCount.incrementAndGet())
                                .block();
                    } finally {
                        latch.countDown();
                    }
                });
            }

            latch.await();
            executor.shutdown();

            assertThat(successCount.get()).isEqualTo(concurrentRequests);
        }

        @Test
        @DisplayName("Debe mantener el orden de procesamiento")
        void shouldMaintainProcessingOrder() {
            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            var transactions = java.util.List.of(
                    createTestTransaction(),
                    createTestTransaction(),
                    createTestTransaction()
            );

            var result = paymentUseCase.processPayment(Mono.just(transactions.get(0)))
                    .then(paymentUseCase.processPayment(Mono.just(transactions.get(1))))
                    .then(paymentUseCase.processPayment(Mono.just(transactions.get(2))));

            StepVerifier.create(result)
                    .assertNext(r -> assertThat(r.getStatus()).isEqualTo("COMPLETED"))
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Idempotencia")
    class IdempotencyScenarios {

        @Test
        @DisplayName("Debe indicar que la transacción ya fue procesada")
        void shouldIndicateTransactionAlreadyProcessed() {
            IdempotencyKey key = createIdempotencyKey("OP-123", "WEB");

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            Transaction tx = createTestTransaction();
            paymentUseCase.processPayment(Mono.just(tx)).block();

            StepVerifier.create(paymentUseCase.isTransactionProcessed(key))
                    .expectNext(true)
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe retornar false para transacción no procesada")
        void shouldReturnFalseForNonProcessedTransaction() {
            IdempotencyKey key = createIdempotencyKey("OP-NEW", "MOBILE");

            StepVerifier.create(paymentUseCase.isTransactionProcessed(key))
                    .expectNext(false)
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Manejo de Fallos Temporales")
    class TemporaryFailureScenarios {

        @Test
        @DisplayName("Debe manejar fallo temporal del servicio de riesgo")
        void shouldHandleTemporaryRiskServiceFailure() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any()))
                    .thenReturn(Mono.error(new RuntimeException("Temporary failure")));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe reintentar automáticamente en fallos temporales")
        void shouldRetryOnTemporaryFailures() {
            Transaction transaction = createTestTransaction();
            AtomicInteger attemptCount = new AtomicInteger(0);

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any()))
                    .thenAnswer(invocation -> {
                        if (attemptCount.incrementAndGet() < 3) {
                            return Mono.error(new RuntimeException("Temporary failure"));
                        }
                        return Mono.just(10);
                    });
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction))
                    .retry(2))
                    .assertNext(result -> assertThat(result.getStatus()).isEqualTo("COMPLETED"))
                    .verifyComplete();
        }
    }
}