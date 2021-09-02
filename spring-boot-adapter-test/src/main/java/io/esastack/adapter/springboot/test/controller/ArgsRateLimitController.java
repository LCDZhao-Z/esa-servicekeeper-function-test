package io.esastack.adapter.springboot.test.controller;

import io.esastack.servicekeeper.core.annotation.ArgsRateLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import io.esastack.servicekeeper.core.annotation.RateLimiter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/argRateLimit")
public class ArgsRateLimitController {

    @GetMapping("/withoutFallback/{name}")
    @ResponseBody
    public String withoutFallback(
            @ArgsRateLimiter(limitRefreshPeriod = "10s", limitForPeriodMap = "{LiMing: 1, WangWu: 2}")
            @PathVariable String name) {
        return name;
    }

    @GetMapping("/withFallbackToValue/{name}")
    @ResponseBody
    @Fallback(fallbackValue = "fallbackValue")
    public String withFallbackToValue(
            @ArgsRateLimiter(limitRefreshPeriod = "10s", limitForPeriodMap = "{LiMing: 1, WangWu: 2}")
            @PathVariable String name) {
        return name;
    }

}
