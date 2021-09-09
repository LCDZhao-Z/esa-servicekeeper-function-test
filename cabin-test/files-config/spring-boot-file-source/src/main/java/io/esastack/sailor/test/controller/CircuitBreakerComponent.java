package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;

@Component
public class CircuitBreakerComponent {

    @CircuitBreaker(ringBufferSizeInClosedState = 4,
            ringBufferSizeInHalfOpenState = 2)
    public String withoutFallback() throws InterruptedException {
        Thread.sleep(100);
        return "withoutFallback";
    }

}
