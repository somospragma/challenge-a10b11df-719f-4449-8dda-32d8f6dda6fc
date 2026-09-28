package com.pragma.payments.infrastructure.adapters.out.risk;

import com.pragma.payments.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para RiskBureauAdapter")
class RiskBureauAdapterTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private RiskBureauAdapter riskBureauAdapter;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transaction = new Transaction(
            UUID.randomUUID().toString(),
            "ORIG-001",
            "ACC-123456",
            new BigDecimal("5000.00"),
            "PESOS",
            "WEB",
            LocalDateTime.now(),
            "COMPLETED"
        );
    }

    @Test
    @DisplayName("getRiskScore retorna código de riesgo válido cuando el servicio responde correctamente")
    void getRiskScore_WhenServiceRespondsSuccessfully_ReturnsRiskCode() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(450));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectNext(450)
            .verifyComplete();
    }

    @Test
    @DisplayName("getRiskScore retorna código de riesgo alto para cuentas de alto riesgo")
    void getRiskScore_WhenAccountIsHighRisk_ReturnsHighRiskCode() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(850));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectNext(850)
            .verifyComplete();
    }

    @Test
    @DisplayName("getRiskScore retorna código de riesgo bajo para cuentas nuevas o de bajo riesgo")
    void getRiskScore_WhenAccountIsLowRisk_ReturnsLowRiskCode() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(150));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectNext(150)
            .verifyComplete();
    }

    @Test
    @DisplayName("getRiskScore lanza excepción cuando el servicio externo retorna error 500")
    void getRiskScore_WhenExternalServiceReturns500_ThrowsException() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class))
            .thenReturn(Mono.error(new RuntimeException("Error del servicio de riesgo")));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectError(RuntimeException.class)
            .verify();
    }

    @Test
    @DisplayName("getRiskScore retorna Mono.empty cuando el servicio no retorna datos")
    void getRiskScore_WhenServiceReturnsNoData_ReturnsEmpty() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.empty());

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .verifyComplete();
    }

    @Test
    @DisplayName("isAccountBlacklisted retorna true cuando la cuenta está en lista negra")
    void isAccountBlacklisted_WhenAccountIsBlacklisted_ReturnsTrue() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/blacklist/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(true));

        StepVerifier.create(riskBureauAdapter.isAccountBlacklisted("ACC-123456"))
            .expectNext(true)
            .verifyComplete();
    }

    @Test
n    @DisplayName("isAccountBlacklisted retorna false cuando la cuenta no está en lista negra")
    void isAccountBlacklisted_WhenAccountIsNotBlacklisted_ReturnsFalse() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/blacklist/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(false));

        StepVerifier.create(riskBureauAdapter.isAccountBlacklisted("ACC-123456"))
            .expectNext(false)
            .verifyComplete();
    }

    @Test
    @DisplayName("isAccountBlacklisted lanza excepción en timeout del servicio externo")
    void isAccountBlacklisted_WhenServiceTimesOut_ThrowsException() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/blacklist/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class))
            .thenReturn(Mono.delay(Duration.ofMillis(500)).map(l -> false));

        StepVerifier.create(riskBureauAdapter.isAccountBlacklisted("ACC-123456")
                .timeout(Duration.ofMillis(100)))
            .expectError()
            .verify();
    }

    @Test
    @DisplayName("getRiskScore lanza excepción cuando la cuenta está en lista negra según el código de riesgo")
    void getRiskScore_WhenAccountIsBlacklistedByRiskCode_ThrowsException() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(999));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectError(IllegalArgumentException.class)
            .verify();
    }

    @Test
    @DisplayName("getRiskScore usa el accountId correcto de la transacción")
    void getRiskScore_UsesCorrectAccountIdFromTransaction() {
        String accountId = "ACC-SPECIFIC-789";
        Transaction specificTransaction = new Transaction(
            UUID.randomUUID().toString(),
            "ORIG-002",
            accountId,
            new BigDecimal("10000.00"),
            "PESOS",
            "MOBILE",
            LocalDateTime.now(),
            "PENDING"
        );

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq(accountId)))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(300));

        StepVerifier.create(riskBureauAdapter.getRiskScore(specificTransaction))
            .expectNext(300)
            .verifyComplete();
    }

    @Test
    @DisplayName("isAccountBlacklisted maneja cuenta con identificador vacío")
    void isAccountBlacklisted_WithEmptyAccountId_HandlesGracefully() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/blacklist/{accountId}"), eq("")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(false));

        StepVerifier.create(riskBureauAdapter.isAccountBlacklisted(""))
            .expectNext(false)
            .verifyComplete();
    }