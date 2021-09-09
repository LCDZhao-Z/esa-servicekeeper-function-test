package io.esastack.adapter.proxy.test.service;


import io.esastack.adapter.proxy.test.exception.FallbackException;
import io.esastack.servicekeeper.adapter.proxy.ServiceKeeperProxyFactory;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.annotation.RateLimiter;
import io.esastack.servicekeeper.core.exception.RateLimitOverflowException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class RateLimitServiceNoInterface {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RateLimitServiceNoInterface proxyNoInterface =
                ServiceKeeperProxyFactory.createProxyNoInterface(new RateLimitServiceNoInterface());

        proxyNoInterface.withoutFallback();
        try {
            proxyNoInterface.withoutFallback();
        } catch (Throwable e) {
            if (e instanceof RateLimitOverflowException) {
                System.out.println("WithoutFallback pass!");
            }
        }

        proxyNoInterface.withFallbackToValue();
        if ("fallbackValue".equals(proxyNoInterface.withFallbackToValue())) {
            System.out.println("FallbackToValue pass!");
        }

        proxyNoInterface.withFallbackToException();
        try {
            proxyNoInterface.withFallbackToException();
        } catch (Throwable e) {
            if (e instanceof FallbackException) {
                System.out.println("WithFallbackToException pass!");
            }
        }

        proxyNoInterface.withFallbackToMethod();
        if ("rateLimitFallback".equals(proxyNoInterface.withFallbackToMethod())) {
            System.out.println("fallbackToMethod pass!");
        }

        proxyNoInterface.asyncWithFallbackToMethod().toCompletableFuture().get();
        if ("rateLimitFallback".equals(proxyNoInterface.asyncWithFallbackToMethod().toCompletableFuture().get())) {
            System.out.println("asyncFallbackToMethod pass!");
        }
    }

    @RateLimiter(value = 1, limitRefreshPeriod = "10s")
    public String withoutFallback() {
        return "rateLimit 2 times in 10s without fallback";
    }

    @RateLimiter(value = 1, limitRefreshPeriod = "10s")
    @Fallback(fallbackValue = "fallbackValue")
    public String withFallbackToValue() {
        return "rateLimit 2 times in 10s with fallback to value";
    }

    @RateLimiter(value = 1, limitRefreshPeriod = "10s")
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public String withFallbackToException() {
        return "rateLimit 2 times in 10s with fallback to exception";
    }

    @RateLimiter(value = 1, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = io.esastack.adapter.proxy.test.fallback.Fallback.class, fallbackMethod = "fallbackMethod")
    public String withFallbackToMethod() {
        return "rateLimit 2 times in 10s with fallback to method";
    }

    @RateLimiter(value = 1, limitRefreshPeriod = "10s")
    public CompletionStage<String> asyncWithoutFallback() {
        return CompletableFuture.completedFuture("async 2 times in 10s without fallback");
    }

    @RateLimiter(value = 1, limitRefreshPeriod = "10s")
    @Fallback(fallbackValue = "fallbackValue")
    public CompletionStage<String> asyncWithFallbackToValue() {
        return CompletableFuture.completedFuture("async 2 times in 10s with fallback to value");
    }

    @RateLimiter(value = 1, limitRefreshPeriod = "10s")
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public CompletionStage<String> asyncWithFallbackToException() {
        return CompletableFuture.completedFuture("async 2 times in 10s with fallback to exception");
    }

    @RateLimiter(value = 1, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = io.esastack.adapter.proxy.test.fallback.Fallback.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncWithFallbackToMethod() {
        return CompletableFuture.completedFuture("async 2 times in 10s with fallback to method");
    }

}
