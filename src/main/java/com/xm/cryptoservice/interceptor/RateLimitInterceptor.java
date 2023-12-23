package com.xm.cryptoservice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.xm.cryptoservice.exception.RateLimitException;
import com.xm.cryptoservice.service.RateLimitService;

/**
 * RateLimitInterceptor class is responsible for limiting the rate of requests
 * to the application.
 * It uses a RateLimiter to limit the rate of requests based on the IP address
 * of the request.
 */
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RateLimitService rateLimitService;

    @Value("${service.rate.limit}")
    private double rateLimit;

    /**
     * This method is called before the actual handler is executed.
     * It checks if the rate limit for the IP address of the request has been
     * exceeded.
     * If the rate limit has been exceeded, it throws a RateLimitException.
     * 
     * @return true if the execution chain should proceed with the next interceptor
     *         or the handler itself.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String ip = request.getRemoteAddr();
        RateLimiter rateLimiter = rateLimitService.getRateLimiter(ip, rateLimit);

        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitException("Too many requests");
        }

        return true;
    }

    /**
     * This method is used for unit tests to set the RateLimitService.
     * 
     * @param rateLimitService the RateLimitService to set
     */
    public void setRateLimitService(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }
}