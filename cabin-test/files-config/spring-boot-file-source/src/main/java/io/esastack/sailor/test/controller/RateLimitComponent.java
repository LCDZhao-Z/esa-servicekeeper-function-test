package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.RateLimiter;
import org.springframework.stereotype.Component;

@Component
public class RateLimitComponent {

    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    public String withoutFallback() {
        return "withoutFallback";
    }

}
