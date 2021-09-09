package io.esastack.adapter.restlight.test.controller;

import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import io.esastack.adapter.restlight.test.exception.FallbackException;
import io.esastack.adapter.restlight.test.fallback.FallbackComponent;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.annotation.RateLimiter;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Controller
@RequestMapping("/rateLimit")
public class RateLimitController {

    @GetMapping("/withoutFallback")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    public String withoutFallback() {
        return "rateLimit 2 times in 10s without fallback";
    }

    @GetMapping("/withFallbackToValue")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackValue = "fallbackValue")
    public String withFallbackToValue() {
        return "rateLimit 2 times in 10s with fallback to value";
    }

    @GetMapping("/withFallbackToException")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public String withFallbackToException() {
        return "rateLimit 2 times in 10s with fallback to exception";
    }

    @GetMapping("/withFallbackToMethod")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = io.esastack.adapter.restlight.test.fallback.Fallback.class, fallbackMethod = "fallbackMethod")
    public String withFallbackToMethod() {
        return "rateLimit 2 times in 10s with fallback to method";
    }

    @GetMapping("/withFallbackToMethodOfComponent")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "fallbackMethod")
    public String withFallbackToMethodOfComponent() {
        return "rateLimit 2 times in 10s with fallback to method of component";
    }

    @GetMapping("/asyncWithoutFallback")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    public CompletionStage<String> asyncWithoutFallback() {
        return CompletableFuture.completedFuture("async 2 times in 10s without fallback");
    }

    @GetMapping("/asyncWithFallbackToValue")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackValue = "fallbackValue")
    public CompletionStage<String> asyncWithFallbackToValue() {
        return CompletableFuture.completedFuture("async 2 times in 10s with fallback to value");
    }

    @GetMapping("/asyncWithFallbackToException")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public CompletionStage<String> asyncWithFallbackToException() {
        return CompletableFuture.completedFuture("async 2 times in 10s with fallback to exception");
    }

    @GetMapping("/asyncWithFallbackToMethod")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = io.esastack.adapter.restlight.test.fallback.Fallback.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncWithFallbackToMethod() {
        return CompletableFuture.completedFuture("async 2 times in 10s with fallback to method");
    }

    @GetMapping("/asyncWithFallbackToMethodOfComponent")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncWithFallbackToMethodOfComponent() {
        return CompletableFuture.completedFuture("async 2 times in 10s with fallback to method of component");
    }

}
