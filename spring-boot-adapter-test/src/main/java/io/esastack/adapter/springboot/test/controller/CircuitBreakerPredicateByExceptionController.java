package io.esastack.adapter.springboot.test.controller;

import io.esastack.adapter.springboot.test.exception.FallbackException;
import io.esastack.adapter.springboot.test.fallback.FallbackComponent;
import io.esastack.servicekeeper.core.annotation.CircuitBreaker;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Controller
@RequestMapping("/circuitBreaker/predicateByException")
public class CircuitBreakerPredicateByExceptionController {

    @GetMapping("/withoutFallback")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 4,
            ringBufferSizeInHalfOpenState = 2)
    public String withoutFallback() {
        throw new RuntimeException();
    }

    @GetMapping("/withFallbackToValue")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 0.33f)
    @Fallback(fallbackValue = "fallbackValue")
    public String withFallbackToValue()  {
        throw new RuntimeException();
    }

    @GetMapping("/withFallbackToException")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 0.33f)
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public String withFallbackToException() {
        throw new RuntimeException();
    }

    @GetMapping("/withFallbackToMethod")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 0.33f)
    @Fallback(fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "fallbackMethod")
    public String withFallbackToMethod() {
        throw new RuntimeException();
    }

    @GetMapping("/withFallbackToMethodOfComponent")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 0.33f)
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "fallbackMethod")
    public String withFallbackToMethodOfComponent() {
        throw new RuntimeException();
    }

    @GetMapping("/asyncWithoutFallback")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 0.33f)
    public CompletionStage<String> asyncWithoutFallback() {
        return CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        });
    }

    @GetMapping("/asyncWithFallbackToValue")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 0.33f)
    @Fallback(fallbackValue = "fallbackValue")
    public CompletionStage<String> asyncWithFallbackToValue() {
        return CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        });
    }

    @GetMapping("/asyncWithFallbackToException")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 0.33f)
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public CompletionStage<String> asyncWithFallbackToException() {
        return CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        });
    }

    @GetMapping("/asyncWithFallbackToMethod")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 0.33f)
    @Fallback(fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncWithFallbackToMethod() {
        return CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        });
    }

    @GetMapping("/asyncWithFallbackToMethodOfComponent")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 0.33f)
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncWithFallbackToMethodOfComponent() {
        return CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        });
    }
}
