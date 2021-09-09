package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.ConcurrentLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Component;

@Component
public class ConcurrentLimitController {

    @ConcurrentLimiter(2)
    @Fallback(fallbackValue = "fallback")
    public String withoutFallback() throws InterruptedException {
        Thread.sleep(20 * 1000);
        return "withoutFallback";
    }

}
