package com.portfolio.alerts.exception;

/**
 * Exception thrown when attempting to create a duplicate subscription.
 */
public class DuplicateSubscriptionException extends RuntimeException {
    public DuplicateSubscriptionException(String message) {
        super(message);
    }

    public DuplicateSubscriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
