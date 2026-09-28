package com.pragma.payments.application.ports.in;

import com.pragma.payments.domain.model.IdempotencyKey;
import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto de entrada para el procesamiento de pagos.
 * Define las operaciones que el dominio expone para procesar transacciones
 * con manejo de concurrencia e idempotencia.
 */
public interface PaymentServicePort {
    /**
     * Procesa una solicitud de pago con idempotencia garantizada.
     * 
     * @param transactionId Identificador único de la transacción generado por el cliente.
     * @param operationNumber Número de operación proporcionado por el canal.
     * @param channel Canal desde el cual se origina la solicitud (ej: "WEB", "MOBILE", "API").
     * @param amount Monto de la transacción.
     * @param currency Moneda de la transacción (ej: "COP", "USD").
     * @param accountId Identificador de la cuenta origen.
     * @param merchantId Identificador del comercio.
     * @return Mono<Transaction> que emite la transacción procesada o error en caso de fallo.
     */
    Mono<Transaction> processPayment(
        UUID transactionId,
        String operationNumber,
        String channel,
        BigDecimal amount,
        String currency,
        String accountId,
        String merchantId
    );

    /**
     * Verifica si una transacción ya fue procesada con la misma clave de idempotencia.
     * 
     * @param idempotencyKey Clave de idempotencia compuesta por operationNumber + channel.
     * @return Mono<Boolean> que emite true si la transacción ya existe, false en caso contrario.
     */
    Mono<Boolean> isTransactionProcessed(IdempotencyKey idempotencyKey);

    /**
     * Recupera una transacción procesada por su clave de idempotencia.
     * 
     * @param idempotencyKey Clave de idempotencia compuesta por operationNumber + channel.
     * @return Mono<Transaction> que emite la transacción si existe, o Mono.empty() si no.
     */
    Mono<Transaction> getProcessedTransaction(IdempotencyKey idempotencyKey);
}