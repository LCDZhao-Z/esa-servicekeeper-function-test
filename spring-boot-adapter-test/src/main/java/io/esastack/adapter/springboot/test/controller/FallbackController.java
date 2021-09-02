package io.esastack.adapter.springboot.test.controller;

import io.esastack.adapter.springboot.test.exception.FallbackException;
import io.esastack.adapter.springboot.test.fallback.FallbackComponent;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.annotation.RateLimiter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletionStage;

@Controller
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/fallbackToValue")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true, fallbackValue = "fallbackValue")
    public String fallbackToValue() {
        throw new RuntimeException();
    }

    @GetMapping("/fallbackToException")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true, fallbackExceptionClass = FallbackException.class)
    public String fallbackToException() {
        throw new RuntimeException();
    }

    @GetMapping("/fallbackToMethod")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true, fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "fallbackMethod")
    public String fallbackToMethod() {
        throw new RuntimeException();
    }

    @GetMapping("/fallbackToMethodOfComponent")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true, fallbackClass = FallbackComponent.class, fallbackMethod = "fallbackMethod")
    public String fallbackToMethodOfComponent() {
        throw new RuntimeException();
    }

    @GetMapping("/asyncFallbackToValue")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true, fallbackValue = "fallbackValue")
    public CompletionStage<String> asyncFallbackToValue() {
        throw new RuntimeException();
    }

    @GetMapping("/asyncFallbackToException")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true, fallbackExceptionClass = FallbackException.class)
    public CompletionStage<String> asyncFallbackToException() {
        throw new RuntimeException();
    }

    @GetMapping("/asyncFallbackToMethod")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true, fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncFallbackToMethod() {
        throw new RuntimeException();
    }

    @GetMapping("/asyncFallbackToMethodOfComponent")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true, fallbackClass = FallbackComponent.class, fallbackMethod = "asyncFallbackMethod")
    public CompletionStage<String> asyncFallbackToMethodOfComponent() {
        throw new RuntimeException();
    }

}
