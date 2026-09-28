package com.pragma.payments.application.ports.out;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para la comunicación con el motor antifraude.
 * Define el contrato que la infraestructura debe implementar para evaluar transacciones.
 */
public interface FraudDetectionPort {
    /**
     * Evalúa una transacción en el motor antifraude.
     * 
     * @param transaction Transacción a evaluar.
     * @return Mono<Boolean> que emite true si la transacción es sospechosa, false si es segura.
     * @throws ExternalServiceTimeoutException si el servicio no responde en el tiempo esperado.
     */
    Mono<Boolean> evaluateTransaction(Transaction transaction);
}