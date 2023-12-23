package com.xm.cryptoservice.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.RateLimiter;

/**
 * RateLimitService class provides services related to rate limiting.
 * It includes a method for getting a RateLimiter for a specific key.
 */
@Service
public class RateLimitService {
    private final ConcurrentHashMap<String, RateLimiter> limiters = new ConcurrentHashMap<>();

    /**
     * Retrieves a RateLimiter for a specific key. If the key does not exist, a new
     * RateLimiter is created.
     * 
     * @param key              the key to retrieve the RateLimiter for
     * @param permitsPerSecond the number of permits per second for the RateLimiter
     * @return the RateLimiter for the specified key
     */
    public RateLimiter getRateLimiter(String key, double permitsPerSecond) {
        return limiters.computeIfAbsent(key, k -> RateLimiter.create(permitsPerSecond));
    }
}