package com.xm.cryptoservice.interceptor;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;

import com.google.common.util.concurrent.RateLimiter;
import com.xm.cryptoservice.exception.RateLimitException;
import com.xm.cryptoservice.service.RateLimitService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "service.rate.limit=1.0"
})
public class RateLimitInterceptorTest {

    private RateLimitService rateLimitService;

    @Autowired
    private RateLimitInterceptor rateLimitInterceptor;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private Object handler;

    @BeforeEach
    public void setUp() {
        rateLimitService = mock(RateLimitService.class);
        rateLimitInterceptor.setRateLimitService(rateLimitService);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handler = new Object();
    }

    @Test
    public void whenRateLimitExceeded_thenThrowException() {
        RateLimiter rateLimiter = RateLimiter.create(1.0);
        when(rateLimitService.getRateLimiter("127.0.0.1", 1.0)).thenReturn(rateLimiter);

        // Acquire permit to exceed rate limit
        rateLimiter.acquire();

        assertThrows(RateLimitException.class, () -> rateLimitInterceptor.preHandle(request, response, handler));
    }

    @Test
    public void whenRateLimitNotExceeded_thenProceed() throws Exception {
        RateLimiter rateLimiter = RateLimiter.create(1.0);
        when(rateLimitService.getRateLimiter("127.0.0.1", 1.0)).thenReturn(rateLimiter);

        assert rateLimitInterceptor.preHandle(request, response, handler);
    }
}