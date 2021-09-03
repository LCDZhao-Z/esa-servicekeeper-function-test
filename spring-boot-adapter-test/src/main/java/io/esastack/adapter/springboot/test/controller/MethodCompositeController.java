package io.esastack.adapter.springboot.test.controller;


import io.esastack.servicekeeper.core.annotation.CircuitBreaker;
import io.esastack.servicekeeper.core.annotation.ConcurrentLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.annotation.RateLimiter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/composite")
public class MethodCompositeController {

    @GetMapping("/withFallback")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 3,
            ringBufferSizeInHalfOpenState = 1,
            maxSpendTimeMs = 30,
            waitDurationInOpenState = "30s")
    @ConcurrentLimiter(2)
    @RateLimiter(value = 4, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true,
            fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class,
            fallbackMethod = "fallbackMethod")
    public String withFallback() throws InterruptedException {
        Thread.sleep(20 * 1000);
        throw new RuntimeException();
    }

}
