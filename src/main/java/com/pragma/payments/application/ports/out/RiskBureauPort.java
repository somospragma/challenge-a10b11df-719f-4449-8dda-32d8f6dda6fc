package com.pragma.payments.application.ports.out;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para la comunicación con el buró de riesgos.
 * Define el contrato que la infraestructura debe implementar para obtener el score de riesgo.
 */
public interface RiskBureauPort {
    /**
     * Obtiene el score de riesgo de una transacción desde el buró de riesgos.
     * 
     * @param transaction Transacción a evaluar.
     * @return Mono<Integer> que emite el score de riesgo (0-1000).
     * @throws ExternalServiceTimeoutException si el servicio no responde en el tiempo esperado.
     */
    Mono<Integer> getRiskScore(Transaction transaction);

    /**
     * Verifica si el cliente asociado a la transacción está en lista negra.
     * 
     * @param accountId Identificador de la cuenta origen.
     * @return Mono<Boolean> que emite true si el cliente está en lista negra, false en caso contrario.
     */
    Mono<Boolean> isAccountBlacklisted(String accountId);
}