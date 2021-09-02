package io.esastack.adapter.springboot.test.controller;

import io.esastack.servicekeeper.core.annotation.ArgsConcurrentLimiter;
import io.esastack.servicekeeper.core.annotation.ConcurrentLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/argConcurrentLimit")
public class ArgConcurrentLimitController {

    @GetMapping("/withoutFallback/{name}")
    @ResponseBody
    public String withoutFallback(
            @ArgsConcurrentLimiter(thresholdMap = "{LiMing: 2, WangWu: 3}")
            @PathVariable String name) throws InterruptedException {
        Thread.sleep(20 * 1000);
        return name;
    }

    @GetMapping("/withFallbackToValue/{name}")
    @ResponseBody
    @Fallback(fallbackValue = "fallbackValue")
    public String withFallbackToValue(
            @ArgsConcurrentLimiter(thresholdMap = "{LiMing: 2, WangWu: 3}")
            @PathVariable String name) throws InterruptedException {
        Thread.sleep(20 * 1000);
        return name;
    }
}
