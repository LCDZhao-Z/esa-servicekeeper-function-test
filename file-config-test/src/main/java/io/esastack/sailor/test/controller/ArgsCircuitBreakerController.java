package io.esastack.sailor.test.controller;

import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.PathVariable;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import io.esastack.servicekeeper.core.annotation.ArgsCircuitBreaker;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/argCircuitBreaker")
public class ArgsCircuitBreakerController {

    @GetMapping("/withoutFallback/{name}")
    @ResponseBody
    public String withoutFallback(
            @ArgsCircuitBreaker(failureRateThresholdMap = "{LiMing: 20.0f, WangWu: 30.0f}",
                    ringBufferSizeInClosedState = 5)
            @PathVariable String name) throws InterruptedException {
        Thread.sleep(100);
        return name;
    }

}
