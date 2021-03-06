package io.esastack.adapter.restlight.test.fallback;

import io.esastack.servicekeeper.core.exception.CircuitBreakerNotPermittedException;
import io.esastack.servicekeeper.core.exception.ConcurrentOverflowException;
import io.esastack.servicekeeper.core.exception.RateLimitOverflowException;

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

    public String fallbackMethod(ConcurrentOverflowException e) {
        return "concurrentFallback";
    }

    public String fallbackMethod(CircuitBreakerNotPermittedException e) {
        return "circuitBreakerFallback";
    }
}
