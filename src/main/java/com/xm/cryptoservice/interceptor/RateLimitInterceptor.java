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

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RateLimitService rateLimitService;

    @Value("${service.rate.limit}")
    private double rateLimit;

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

    // used for unit tests
    public void setRateLimitService(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }
}