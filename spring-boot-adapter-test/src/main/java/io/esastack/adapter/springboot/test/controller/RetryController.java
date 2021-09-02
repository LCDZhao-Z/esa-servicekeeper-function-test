package io.esastack.adapter.springboot.test.controller;

import io.esastack.servicekeeper.core.annotation.Backoff;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/retry")
public class RetryController {
    private final static AtomicInteger REQUEST_COUNT = new AtomicInteger(0);
    private final static int RUNTIME_EXCEPTION_PERIOD = 6;
    private final static int MAX_RETRY_TIMES = 5;


    @GetMapping("/retryWithoutFallback")
    @Retryable(maxAttempts = MAX_RETRY_TIMES, includeExceptions = {IOException.class},
            excludeExceptions = {RuntimeException.class},
            backoff = @Backoff(delay = 1000, maxDelay = 5000, multiplier = 1.5))
    @ResponseBody
    public String retryWithoutFallback() throws IOException {
        int retryCount = REQUEST_COUNT.addAndGet(1);
        System.out.println("retry:---------------" + retryCount + "------------" + System.currentTimeMillis());
        if (retryCount % RUNTIME_EXCEPTION_PERIOD == 0) {
            throw new RuntimeException();
        } else {
            throw new IOException();
        }
    }

    @GetMapping("/retryWithFallback")
    @Retryable(maxAttempts = MAX_RETRY_TIMES, includeExceptions = {IOException.class},
            excludeExceptions = {RuntimeException.class},
            backoff = @Backoff(delay = 1000, maxDelay = 5000, multiplier = 1.5))
    @Fallback(fallbackValue = "fallbackValue", alsoApplyToBizException = true)
    @ResponseBody
    public String retryWithFallback() throws IOException {
        int retryCount = REQUEST_COUNT.addAndGet(1);
        System.out.println("retry:---------------" + retryCount + "------------" + System.currentTimeMillis());
        if (retryCount % RUNTIME_EXCEPTION_PERIOD == 0) {
            throw new RuntimeException();
        } else {
            throw new IOException();
        }
    }

    @GetMapping("/clean")
    public void clean() throws IOException {
        REQUEST_COUNT.set(0);
    }


}
