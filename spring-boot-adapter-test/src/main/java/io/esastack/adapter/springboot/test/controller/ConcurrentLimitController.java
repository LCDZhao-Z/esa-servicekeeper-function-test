package io.esastack.adapter.springboot.test.controller;

import io.esastack.adapter.springboot.test.exception.FallbackException;
import io.esastack.adapter.springboot.test.fallback.FallbackComponent;
import io.esastack.servicekeeper.core.annotation.ConcurrentLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Controller
@RequestMapping("/concurrentLimit/pre")
public class ConcurrentLimitController {

    @GetMapping("/withoutFallback")
    @ResponseBody
    @ConcurrentLimiter(2)
    public String withoutFallback() throws InterruptedException {
        Thread.sleep(20 * 1000);
        return "withoutFallback";
    }

    @GetMapping("/withFallbackToValue")
    @ResponseBody
    @ConcurrentLimiter(2)
    @Fallback(fallbackValue = "fallbackValue")
    public String withFallbackToValue() throws InterruptedException {
        Thread.sleep(20 * 1000);
        return "withFallbackToValue";
    }

    @GetMapping("/withFallbackToException")
    @ResponseBody
    @ConcurrentLimiter(2)
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public String withFallbackToException() throws InterruptedException {
        Thread.sleep(20 * 1000);
        return "withFallbackToException";
    }

    @GetMapping("/withFallbackToMethod")
    @ResponseBody
    @ConcurrentLimiter(2)
    @Fallback(fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "fallbackMethod")
    public String withFallbackToMethod() throws InterruptedException {
        Thread.sleep(20 * 1000);
        return "withFallbackToMethod";
    }

    @GetMapping("/withFallbackToMethodOfComponent")
    @ResponseBody
    @ConcurrentLimiter(2)
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "fallbackMethod")
    public String withFallbackToMethodOfComponent() throws InterruptedException {
        Thread.sleep(20 * 1000);
        return "withFallbackToMethodOfComponent";
    }

    @GetMapping("/asyncWithoutFallback")
    @ResponseBody
    @ConcurrentLimiter(2)
    public CompletionStage<String> asyncWithoutFallback() throws InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(20 * 1000);
            } catch (Throwable ignored) {
            }
            return "asyncWithoutFallback";
        });
    }

    @GetMapping("/asyncWithFallbackToValue")
    @ResponseBody
    @ConcurrentLimiter(2)
    @Fallback(fallbackValue = "fallbackValue")
    public CompletionStage<String> asyncWithFallbackToValue() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(20 * 1000);
            } catch (Throwable ignored) {
            }
            return "asyncWithFallbackToValue";
        });
    }

    @GetMapping("/asyncWithFallbackToException")
    @ResponseBody
    @ConcurrentLimiter(2)
    @Fallback(fallbackExceptionClass = FallbackException.class)
    public CompletionStage<String> asyncWithFallbackToException() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(20 * 1000);
            } catch (Throwable ignored) {
            }
            return "asyncWithFallbackToException";
        });
    }

    @GetMapping("/asyncWithFallbackToMethod")
    @ResponseBody
    @ConcurrentLimiter(2)
    @Fallback(fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncWithFallbackToMethod() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(20 * 1000);
            } catch (Throwable ignored) {
            }
            return "asyncWithFallbackToMethod";
        });
    }

    @GetMapping("/asyncWithFallbackToMethodOfComponent")
    @ResponseBody
    @ConcurrentLimiter(2)
    @Fallback(fallbackClass = FallbackComponent.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncWithFallbackToMethodOfComponent() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(20 * 1000);
            } catch (Throwable ignored) {
            }
            return "asyncWithFallbackToMethodOfComponent";
        });
    }
}
