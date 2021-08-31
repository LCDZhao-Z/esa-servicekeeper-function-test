package io.esastack.adapter.springboot.test.controller;

import io.esastack.adapter.springboot.test.exception.FallbackException;
import io.esastack.adapter.springboot.test.fallback.FallbackComponent;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.annotation.RateLimiter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Controller
public class RateLimitController {

    @GetMapping("/rateLimitWithoutFallback")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    public String rateLimitWithoutFallback() {
        return "rateLimit 2 times in 10s without fallback";
    }

    @GetMapping("/rateLimitWithFallbackToValue")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackValue = "fallbackValue")
    public String rateLimitWithFallbackToValue() {
        return "rateLimit 2 times in 10s with fallback to value";
    }

    @GetMapping("/rateLimitWithFallbackToException")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public String rateLimitWithFallbackToException() {
        return "rateLimit 2 times in 10s with fallback to exception";
    }

    @GetMapping("/rateLimitWithFallbackToMethod")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "fallbackMethod")
    public String rateLimitWithFallbackToMethod() {
        return "rateLimit 2 times in 10s with fallback to method";
    }

    @GetMapping("/rateLimitWithFallbackToMethodOfComponent")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "fallbackMethod")
    public String rateLimitWithFallbackToMethodOfComponent() {
        return "rateLimit 2 times in 10s with fallback to method of component";
    }

    @GetMapping("/asyncRateLimitWithoutFallback")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    public CompletionStage<String> asyncRateLimitWithoutFallback() {
        return CompletableFuture.completedFuture("asyncRateLimit 2 times in 10s without fallback");
    }

    @GetMapping("/asyncRateLimitWithFallbackToValue")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackValue = "fallbackValue")
    public CompletionStage<String> asyncRateLimitWithFallbackToValue() {
        return CompletableFuture.completedFuture("asyncRateLimit 2 times in 10s with fallback to value");
    }

    @GetMapping("/asyncRateLimitWithFallbackToException")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public CompletionStage<String> asyncRateLimitWithFallbackToException() {
        return CompletableFuture.completedFuture("asyncRateLimit 2 times in 10s with fallback to exception");
    }

    @GetMapping("/asyncRateLimitWithFallbackToMethod")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncRateLimitWithFallbackToMethod() {
        return CompletableFuture.completedFuture("asyncRateLimit 2 times in 10s with fallback to method");
    }

    @GetMapping("/asyncRateLimitWithFallbackToMethodOfComponent")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncRateLimitWithFallbackToMethodOfComponent() {
        return CompletableFuture.completedFuture("asyncRateLimit 2 times in 10s with fallback to method of component");
    }

}
