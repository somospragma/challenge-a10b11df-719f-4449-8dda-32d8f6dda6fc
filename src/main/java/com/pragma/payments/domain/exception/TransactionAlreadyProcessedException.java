package com.pragma.payments.domain.exception;


import com.pragma.payments.domain.model.Transaction;
import java.time.Instant;
import java.util.UUID;

public class TransactionAlreadyProcessedException extends RuntimeException {
    
    private final String idempotencyKey;
    private final String channel;
    private final String transactionId;
    private final Instant originalProcessingTime;
    private final String operationNumber;
    private static final long serialVersionUID = 1L;
    
    public TransactionAlreadyProcessedException(String idempotencyKey, String channel, 
                                                  String transactionId, Instant originalProcessingTime,
                                                  String operationNumber) {
        super(buildMessage(idempotencyKey, channel, transactionId, operationNumber));
        this.idempotencyKey = idempotencyKey;
        this.channel = channel;
        this.transactionId = transactionId;
        this.originalProcessingTime = originalProcessingTime;
        this.operationNumber = operationNumber;
        validateInvariant();
    }
    
    private void validateInvariant() {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("Idempotency key cannot be null or empty");
        }
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("Channel cannot be null or empty");
        }
    }
    
    private static String buildMessage(String idempotencyKey, String channel, 
                                        String transactionId, String operationNumber) {
        return String.format("Transaction with idempotency key '%s' (operation: %s, channel: %s) " +
                            "has already been processed. Original transaction ID: %s",
                            idempotencyKey, operationNumber, channel, transactionId);
    }
    
    public String getIdempotencyKey() {
        return idempotencyKey;
    }
    
    public String getChannel() {
        return channel;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public Instant getOriginalProcessingTime() {
        return originalProcessingTime;
    }
    
    public String getOperationNumber() {
        return operationNumber;
    }
    
    public long getTimeSinceOriginalProcessing() {
        if (originalProcessingTime == null) {
            return 0L;
        }
        return Instant.now().toEpochMilli() - originalProcessingTime.toEpochMilli();
    }
    
    public boolean isRecentProcessing(long thresholdMillis) {
        return getTimeSinceOriginalProcessing() < thresholdMillis;
    }
    
    @Override
    public String toString() {
        return "TransactionAlreadyProcessedException{" +
                "idempotencyKey='" + idempotencyKey + '\'' +
                ", channel='" + channel + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", originalProcessingTime=" + originalProcessingTime +
                ", operationNumber='" + operationNumber + '\'' +
                '}';
    }
}