package com.pragma.payments.domain.exception;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class ExternalServiceTimeoutException extends RuntimeException {
    
    private final String serviceName;
    private final String endpoint;
    private final Duration configuredTimeout;
    private final Duration actualDuration;
    private final Instant timeoutOccurredAt;
    private final String requestId;
    private final Map<String, Object> metadata;
    private static final long serialVersionUID = 1L;
    
    public ExternalServiceTimeoutException(String serviceName, String endpoint, 
                                            Duration configuredTimeout, Duration actualDuration) {
        super(buildMessage(serviceName, endpoint, configuredTimeout, actualDuration));
        this.serviceName = serviceName;
        this.endpoint = endpoint;
        this.configuredTimeout = configuredTimeout;
        this.actualDuration = actualDuration;
        this.timeoutOccurredAt = Instant.now();
        this.requestId = UUID.randomUUID().toString();
        this.metadata = Map.of();
        validateInvariant();
    }
    
    public ExternalServiceTimeoutException(String serviceName, String endpoint, 
                                            Duration configuredTimeout, Duration actualDuration,
                                            Map<String, Object> metadata) {
        super(buildMessage(serviceName, endpoint, configuredTimeout, actualDuration));
        this.serviceName = serviceName;
        this.endpoint = endpoint;
        this.configuredTimeout = configuredTimeout;
        this.actualDuration = actualDuration;
        this.timeoutOccurredAt = Instant.now();
        this.requestId = UUID.randomUUID().toString();
        this.metadata = metadata != null ? Map.copyOf(metadata) : Map.of();
        validateInvariant();
    }
    
    private void validateInvariant() {
        if (serviceName == null || serviceName.isBlank()) {
            throw new IllegalArgumentException("Service name cannot be null or empty");
        }
        if (configuredTimeout == null || configuredTimeout.isNegative()) {
            throw new IllegalArgumentException("Configured timeout must be positive");
        }
        if (actualDuration == null) {
            throw new IllegalArgumentException("Actual duration cannot be null");
        }
    }
    
    private static String buildMessage(String serviceName, String endpoint, 
                                        Duration configuredTimeout, Duration actualDuration) {
        return String.format("Timeout exceeded calling service '%s' at endpoint '%s'. " +
                            "Configured timeout: %dms, Actual duration: %dms",
                            serviceName, endpoint, 
                            configuredTimeout.toMillis(), actualDuration.toMillis());
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public String getEndpoint() {
        return endpoint;
    }
    
    public Duration getConfiguredTimeout() {
        return configuredTimeout;
    }
    
    public Duration getActualDuration() {
        return actualDuration;
    }
    
    public Instant getTimeoutOccurredAt() {
        return timeoutOccurredAt;
    }
    
    public String getRequestId() {
        return requestId;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public double getTimeoutRatio() {
        if (configuredTimeout.toMillis() == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return (double) actualDuration.toMillis() / configuredTimeout.toMillis();
    }
    
    public boolean isRetryWorthwhile(int maxRetries, long retryDelayMillis) {
        return getTimeoutRatio() < 2.0 && maxRetries > 0;
    }
    
    @Override
    public String toString() {
        return "ExternalServiceTimeoutException{" +
                "serviceName='" + serviceName + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", configuredTimeout=" + configuredTimeout +
                ", actualDuration=" + actualDuration +
                ", timeoutOccurredAt=" + timeoutOccurredAt +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}