package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.Backoff;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.annotation.RateLimiter;
import io.esastack.servicekeeper.core.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RetryController {
    private final static AtomicInteger REQUEST_COUNT = new AtomicInteger(0);
    private final static int RUNTIME_EXCEPTION_PERIOD = 6;
    private final static int MAX_RETRY_TIMES = 5;

    @Retryable(maxAttempts = MAX_RETRY_TIMES, includeExceptions = {IOException.class},
            excludeExceptions = {RuntimeException.class},
            backoff = @Backoff(delay = 1000, maxDelay = 5000, multiplier = 1.5))
    @Fallback(fallbackValue = "fallbackValue", alsoApplyToBizException = true)
    public String retryWithoutFallback() throws IOException {
        int retryCount = REQUEST_COUNT.addAndGet(1);
        System.out.println("retry:---------------" + retryCount + "------------" + System.currentTimeMillis());
        if (retryCount % RUNTIME_EXCEPTION_PERIOD == 0) {
            System.out.println("don,t retry");
            throw new RuntimeException();
        } else {
            System.out.println("retry");
            throw new IOException();
        }
    }

    @Retryable(maxAttempts = MAX_RETRY_TIMES,
            excludeExceptions = {IOException.class},
            backoff = @Backoff(delay = 1000, maxDelay = 2000, multiplier = 1.5))
    @Fallback(fallbackValue = "fallbackValue", alsoApplyToBizException = true)
    @RateLimiter(value = 1, limitRefreshPeriod = "50s")
    public String retryWithFallback() throws IOException {
        int retryCount = REQUEST_COUNT.addAndGet(1);
        System.out.println("retry:---------------" + retryCount + "------------" + System.currentTimeMillis());
        if (retryCount % RUNTIME_EXCEPTION_PERIOD == 0) {
            throw new IOException();
        } else {
            throw new RuntimeException();
        }
    }

}
