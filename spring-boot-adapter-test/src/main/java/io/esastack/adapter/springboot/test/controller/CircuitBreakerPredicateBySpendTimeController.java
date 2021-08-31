package io.esastack.adapter.springboot.test.controller;

import io.esastack.adapter.springboot.test.exception.FallbackException;
import io.esastack.adapter.springboot.test.fallback.FallbackComponent;
import io.esastack.servicekeeper.core.annotation.CircuitBreaker;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.moats.circuitbreaker.predicate.PredicateBySpendTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Controller
@RequestMapping("/circuitBreaker/predicateBySpendTime")
public class CircuitBreakerPredicateBySpendTimeController {

    @GetMapping("/withoutFallback")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    public String withoutFallback() {
        try {
            Thread.sleep(50);
        } catch (Throwable ignored) {
        }
        return "result";
    }

    @GetMapping("/withFallbackToValue")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    @Fallback(fallbackValue = "fallbackValue")
    public String withFallbackToValue() {
        try {
            Thread.sleep(50);
        } catch (Throwable ignored) {
        }
        return "result";
    }

    @GetMapping("/withFallbackToException")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public String withFallbackToException() {
        try {
            Thread.sleep(50);
        } catch (Throwable ignored) {
        }
        return "result";
    }

    @GetMapping("/withFallbackToMethod")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    @Fallback(fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "fallbackMethod")
    public String withFallbackToMethod() {
        try {
            Thread.sleep(50);
        } catch (Throwable ignored) {
        }
        return "result";
    }

    @GetMapping("/withFallbackToMethodOfComponent")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "fallbackMethod")
    public String withFallbackToMethodOfComponent() {
        try {
            Thread.sleep(50);
        } catch (Throwable ignored) {
        }
        return "result";
    }

    @GetMapping("/asyncWithoutFallback")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    public CompletionStage<String> asyncWithoutFallback() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(50);
            } catch (Throwable ignored) {
            }
            return "result";
        });
    }

    @GetMapping("/asyncWithFallbackToValue")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    @Fallback(fallbackValue = "fallbackValue")
    public CompletionStage<String> asyncWithFallbackToValue() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(50);
            } catch (Throwable ignored) {
            }
            return "result";
        });
    }

    @GetMapping("/asyncWithFallbackToException")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public CompletionStage<String> asyncWithFallbackToException() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(50);
            } catch (Throwable ignored) {
            }
            return "result";
        });
    }

    @GetMapping("/asyncWithFallbackToMethod")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    @Fallback(fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncWithFallbackToMethod() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(50);
            } catch (Throwable ignored) {
            }
            return "result";
        });
    }

    @GetMapping("/asyncWithFallbackToMethodOfComponent")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 2,
            failureRateThreshold = 33f,
            predicateStrategy = PredicateBySpendTime.class,
            maxSpendTimeMs = 30)
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncWithFallbackToMethodOfComponent() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(50);
            } catch (Throwable ignored) {
            }
            return "result";
        });
    }
}
