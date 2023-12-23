package com.xm.cryptoservice.exception;

/**
 * RateLimitException class is a custom exception that is thrown when a rate
 * limit is exceeded.
 * It extends the RuntimeException class, meaning it's an unchecked exception.
 */
public class RateLimitException extends RuntimeException {

    /**
     * Constructor for the RateLimitException class.
     * It calls the superclass constructor with the provided message.
     * 
     * @param message the detail message, saved for later retrieval by the
     *                Throwable.getMessage() method.
     */
    public RateLimitException(String message) {
        super(message);
    }
}