package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.ArgsConcurrentLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Component;

@Component
public class ArgConcurrentLimitController {

    @Fallback(fallbackValue = "fallbackValue")
    public String withFallbackToValue(
            @ArgsConcurrentLimiter(thresholdMap = "{LiMing: 2, WangWu: 3}")
                    String name) throws InterruptedException {
        Thread.sleep(20 * 1000);
        return name;
    }

}
