package io.esastack.sailor.test.controller;

import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.PathVariable;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import io.esastack.servicekeeper.core.annotation.ArgsConcurrentLimiter;
import io.esastack.servicekeeper.core.annotation.Fallback;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/argConcurrentLimit")
public class ArgConcurrentLimitController {

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
