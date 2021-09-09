package io.esastack.adapter.proxy.test.fallback;

import io.esastack.servicekeeper.core.exception.CircuitBreakerNotPermittedException;
import io.esastack.servicekeeper.core.exception.ConcurrentOverFlowException;
import io.esastack.servicekeeper.core.exception.RateLimitOverflowException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

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

    public CompletionStage<String> asyncFallbackMethod(RateLimitOverflowException e) {
        return CompletableFuture.completedFuture("rateLimitFallback");
    }

    public String fallbackMethod(ConcurrentOverFlowException e) {
        return "concurrentFallback";
    }

    public String fallbackMethod(CircuitBreakerNotPermittedException e) {
        return "circuitBreakerFallback";
    }
}
