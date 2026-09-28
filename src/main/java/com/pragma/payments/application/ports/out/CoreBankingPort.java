package com.pragma.payments.application.ports.out;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para la comunicación con el core bancario.
 * Define el contrato que la infraestructura debe implementar para enviar
 * transacciones al sistema bancario central.
 * 
 * Este puerto sigue el patrón de puertos y adaptadores, permitiendo que
 * el dominio permanezca agnóstico de la implementación concreta del core bancario.
 */
public interface CoreBankingPort {
    
    /**
     * Envía una transacción validada al core bancario para su procesamiento.
     * 
     * @param transaction la transacción a procesar, con toda la información necesaria
     * @return Mono que emite la transacción actualizada con el estado final del core bancario,
     *         o un error en caso de fallo de comunicación
     */
    Mono<Transaction> sendTransaction(Transaction transaction);
    
    /**
     * Consulta el estado de una transacción previamente enviada al core bancario.
     * 
     * @param transactionId identificador único de la transacción en el sistema
     * @return Mono que emite el estado actual de la transacción, o vacío si no existe
     */
    Mono<Transaction> getTransactionStatus(String transactionId);
    
    /**
     * Verifica la conectividad con el core bancario.
     * Utilizado para health checks y circuit breaker.
     * 
     * @return Mono que emite true si el core está disponible, false en caso contrario
     */
    Mono<Boolean> isAvailable();
}