package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Component;

@Component
public class ArgsCircuitBreakerController {

    @Fallback(fallbackValue = "fallbackValue")
    public String withoutFallback(
            @io.esastack.servicekeeper.core.annotation.ArgsCircuitBreaker(failureRateThresholdMap = "{LiMing: 20.0f, WangWu: 30.0f}",
                    ringBufferSizeInClosedState = 50) String name) throws InterruptedException {
        Thread.sleep(100);
        return name;
    }

}
