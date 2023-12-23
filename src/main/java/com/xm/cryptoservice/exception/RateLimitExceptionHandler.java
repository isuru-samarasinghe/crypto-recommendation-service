package com.xm.cryptoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RateLimitExceptionHandler class is responsible for handling
 * RateLimitException.
 * It is annotated with @ControllerAdvice, meaning it provides global exception
 * handling.
 */
@ControllerAdvice
public class RateLimitExceptionHandler {

    /**
     * This method handles RateLimitException.
     * When a RateLimitException is thrown, this method will be invoked and it will
     * return a HTTP status of TOO_MANY_REQUESTS.
     */
    @ExceptionHandler(RateLimitException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleRateLimitException() {
        // You can add additional handling logic here if needed
    }
}