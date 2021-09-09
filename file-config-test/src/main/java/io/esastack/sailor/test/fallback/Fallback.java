package io.esastack.sailor.test.fallback;

import io.esastack.servicekeeper.core.exception.CircuitBreakerNotPermittedException;
import io.esastack.servicekeeper.core.exception.ConcurrentOverFlowException;
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

    public String fallbackMethod(ConcurrentOverFlowException e) {
        return "concurrentFallback";
    }

    public String fallbackMethod(CircuitBreakerNotPermittedException e) {
        return "circuitBreakerFallback";
    }
}
