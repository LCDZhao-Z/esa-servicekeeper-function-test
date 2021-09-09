package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.CircuitBreaker;
import io.esastack.servicekeeper.core.annotation.ConcurrentLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.annotation.RateLimiter;
import org.springframework.stereotype.Component;

@Component
public class MethodCompositeComponent {

    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 1,
            maxSpendTimeMs = 30,
            waitDurationInOpenState = "30s")
    @ConcurrentLimiter(2)
    @RateLimiter(value = 4, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true,
            fallbackClass = io.esastack.sailor.test.fallback.Fallback.class,
            fallbackMethod = "fallbackMethod")
    public String withFallback() throws InterruptedException {
        Thread.sleep(20 * 1000);
        throw new RuntimeException();
    }

}
