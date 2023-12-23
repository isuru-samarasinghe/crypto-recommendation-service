package com.xm.cryptoservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xm.cryptoservice.interceptor.RateLimitInterceptor;

/**
 * WebConfig class is responsible for configuring the web-related settings for
 * the application.
 * It sets up the interceptors for the application.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;

    /**
     * This method adds the RateLimitInterceptor to the InterceptorRegistry.
     * The RateLimitInterceptor is used to limit the rate of requests to the
     * application.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor);
    }
}