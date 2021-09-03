package io.esastack.adapter.springboot.test.fallback;

import io.esastack.servicekeeper.core.exception.CircuitBreakerNotPermittedException;
import io.esastack.servicekeeper.core.exception.ConcurrentOverFlowException;
import io.esastack.servicekeeper.core.exception.RateLimitOverflowException;

import java.io.IOException;

public class Fallback {

    public String fallbackMethod() {
        return "fallbackMethod";
    }

    public String fallbackMethod(Throwable e) {
        return "bizFallback";
    }

    public String fallbackMethod(RateLimitOverflowException e) {
        return "rateLimitFallback";
    }

    public String fallbackMethod(ConcurrentOverFlowException e) {
        return "concurrentFallback";
    }

    public String fallbackMethod(CircuitBreakerNotPermittedException e) {
        return "circuitBreakerFallback";
    }
}
