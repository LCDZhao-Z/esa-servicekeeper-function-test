package io.esastack.adapter.springboot.test.controller;

import io.esastack.servicekeeper.core.annotation.ArgsCircuitBreaker;
import io.esastack.servicekeeper.core.annotation.ArgsConcurrentLimiter;
import io.esastack.servicekeeper.core.annotation.ArgsRateLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/argComposite")
public class ArgCompositeController {

    @GetMapping("/withFallback/{name}")
    @ResponseBody
    @Fallback(alsoApplyToBizException = true,
            fallbackClass = io.esastack.adapter.springboot.test.fallback.Fallback.class,
            fallbackMethod = "fallbackMethod")
    public String withFallback(
            @ArgsRateLimiter(limitRefreshPeriod = "10s", limitForPeriodMap = "{LiMing: 4, WangWu: 2}")
            @ArgsConcurrentLimiter(thresholdMap = "{LiMing: 2, WangWu: 3}")
            @ArgsCircuitBreaker(failureRateThresholdMap = "{LiMing: 20.0f, WangWu: 30.0f}",
                    ringBufferSizeInClosedState = 3)
            @PathVariable String name) throws InterruptedException {
        Thread.sleep(2 * 1000);
        throw new RuntimeException();
    }

}
