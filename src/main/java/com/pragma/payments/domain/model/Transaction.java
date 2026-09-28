package com.pragma.payments.domain.model;

import com.pragma.payments.domain.exception.TransactionAlreadyProcessedException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Entidad de dominio que representa una transacción financiera.
 * Encapsula toda la información necesaria para procesar un pago,
 * incluyendo validaciones de negocio y manejo de idempotencia.
 * 
 * La validación de idempotencia se realiza mediante la clave compuesta
 * formada por el número de operación y el canal de origen.
 */
public class Transaction {
    
    @NotBlank(message = "El ID de transacción es obligatorio")
    private String id;
    
    @NotNull(message = "La clave de idempotencia es obligatoria")
    @Valid
    private IdempotencyKey idempotencyKey;
    
    @NotBlank(message = "El número de operación es obligatorio")
    @Size(min = 1, max = 50, message = "El número de operación debe tener entre 1 y 50 caracteres")
    private String operationNumber;
    
    @NotBlank(message = "El canal de origen es obligatorio")
    @Size(min = 1, max = 20, message = "El canal debe tener entre 1 y 20 caracteres")
    private String channel;
    
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    private BigDecimal amount;
    
    @NotBlank(message = "La cuenta de origen es obligatoria")
    private String sourceAccount;
    
    @NotBlank(message = "La cuenta de destino es obligatoria")
    private String destinationAccount;
    
    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe ser un código ISO de 3 letras")
    private String currency;
    
    @NotBlank(message = "El tipo de transacción es obligatorio")
    private String transactionType;
    
    private TransactionStatus status;
    
    private String description;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime processedAt;
    
    private String externalReference;
    
    private Integer riskScore;
    
    private Boolean fraudDetected;
    
    public enum TransactionStatus {
        PENDING,
        VALIDATING,
        PROCESSING,
        COMPLETED,
        FAILED,
        REJECTED,
        DUPLICATE
    }
    
    public Transaction() {
        this.id = UUID.randomUUID().toString();
        this.status = TransactionStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }
    
    public Transaction(IdempotencyKey idempotencyKey, String operationNumber, String channel,
                       BigDecimal amount, String sourceAccount, String destinationAccount,
                       String currency, String transactionType) {
        this();
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = operationNumber;
        this.channel = channel;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.currency = currency;
        this.transactionType = transactionType;
    }
    
    public void validateNotAlreadyProcessed() {
        if (this.status == TransactionStatus.COMPLETED || 
            this.status == TransactionStatus.DUPLICATE) {
            throw new TransactionAlreadyProcessedException(
                "La transacción ya fue procesada: " + this.idempotencyKey.toString()
            );
        }
    }
    
    public void markAsDuplicate() {
        this.status = TransactionStatus.DUPLICATE;
        this.processedAt = LocalDateTime.now();
    }
    
    public void markAsProcessing() {
        this.status = TransactionStatus.PROCESSING;
    }
    
    public void markAsCompleted(String externalReference) {
        this.status = TransactionStatus.COMPLETED;
        this.externalReference = externalReference;
        this.processedAt = LocalDateTime.now();
    }
    
    public void markAsFailed(String description) {
        this.status = TransactionStatus.FAILED;
        this.description = description;
        this.processedAt = LocalDateTime.now();
    }
    
    public void markAsRejected(String reason) {
        this.status = TransactionStatus.REJECTED;
        this.description = reason;
        this.processedAt = LocalDateTime.now();
    }
    
    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }
    
    public void setFraudDetected(Boolean fraudDetected) {
        this.fraudDetected = fraudDetected;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getId() {
        return id;
    }
    
    public IdempotencyKey getIdempotencyKey() {
        return idempotencyKey;
    }
    
    public String getOperationNumber() {
        return operationNumber;
    }
    
    public String getChannel() {
        return channel;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public String getSourceAccount() {
        return sourceAccount;
    }
    
    public String getDestinationAccount() {
        return destinationAccount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public String getTransactionType() {
        return transactionType;
    }
    
    public TransactionStatus getStatus() {
        return status;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getProcessedAt() {
        return processedAt;
    }
    
    public String getExternalReference() {
        return externalReference;
    }
    
    public Integer getRiskScore() {
        return riskScore;
    }
    
    public Boolean getFraudDetected() {
        return fraudDetected;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setIdempotencyKey(IdempotencyKey idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
    
    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }
    
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    
    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }
    
    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", idempotencyKey=" + idempotencyKey +
                ", operationNumber='" + operationNumber + '\'' +
                ", channel='" + channel + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}