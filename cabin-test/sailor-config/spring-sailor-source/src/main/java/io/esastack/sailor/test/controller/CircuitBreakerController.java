package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.CircuitBreaker;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Component;

@Component
public class CircuitBreakerController {

    @CircuitBreaker(ringBufferSizeInClosedState = 4,
            ringBufferSizeInHalfOpenState = 2)
    @Fallback(fallbackValue = "fallback")
    public String withoutFallback() throws InterruptedException {
        Thread.sleep(100);
        return "withoutFallback";
    }

}
