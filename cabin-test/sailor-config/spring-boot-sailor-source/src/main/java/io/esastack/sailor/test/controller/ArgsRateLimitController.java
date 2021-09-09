package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.ArgsRateLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Component;

@Component
public class ArgsRateLimitController {

    @Fallback(fallbackValue = "fallback")
    public String withoutFallback(
            @ArgsRateLimiter(limitRefreshPeriod = "10s", limitForPeriodMap = "{LiMing: 1, WangWu: 2}")
                    String name) {
        return name;
    }

}
