package io.esastack.adapter.springboot.test.controller;

import io.esastack.servicekeeper.core.annotation.ArgsCircuitBreaker;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/argCircuitBreaker")
public class ArgsCircuitBreakerController {

    @GetMapping("/withoutFallback/{name}")
    @ResponseBody
    public String withoutFallback(
            @ArgsCircuitBreaker(failureRateThresholdMap = "{LiMing: 20.0f, WangWu: 30.0f}",
                    ringBufferSizeInClosedState = 3)
            @PathVariable String name) throws InterruptedException {
        throw new RuntimeException();
    }

    @GetMapping("/withFallbackToValue/{name}")
    @ResponseBody
    @Fallback(fallbackValue = "fallbackValue")
    public String withFallbackToValue(
            @ArgsCircuitBreaker(failureRateThresholdMap = "{LiMing: 20.0f, WangWu: 30.0f}",
                    ringBufferSizeInClosedState = 3)
            @PathVariable String name) throws InterruptedException {
        throw new RuntimeException();
    }
}
