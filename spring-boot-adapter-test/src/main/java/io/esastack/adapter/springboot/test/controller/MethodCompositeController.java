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

    //一直快速请求，前10s，2次正常通过，但一直再加载，第3次ConcurrentLimiter，第4次及10s内一直RateLimiter，
    //第10s到第20s的前三次ConcurrentLimiter，后面一直RateLimiter。20s后先CircuitBreaker
    @GetMapping("/withFallback")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 2,
            ringBufferSizeInHalfOpenState = 1,
            maxSpendTimeMs = 30,
            waitDurationInOpenState = "30s")
    @ConcurrentLimiter(2)
    @RateLimiter(value = 3, limitRefreshPeriod = "10s")
    @Fallback(alsoApplyToBizException = true,
            fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class,
            fallbackMethod = "fallbackMethod")
    public String withFallback() throws InterruptedException {
        Thread.sleep(20 * 1000);
        throw new RuntimeException();
    }

}
