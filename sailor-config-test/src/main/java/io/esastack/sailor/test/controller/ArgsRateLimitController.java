package io.esastack.sailor.test.controller;

import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.PathVariable;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import io.esastack.servicekeeper.core.annotation.ArgsRateLimiter;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/argRateLimit")
public class ArgsRateLimitController {

    @GetMapping("/withoutFallback/{name}")
    @ResponseBody
    public String withoutFallback(
            @ArgsRateLimiter(limitRefreshPeriod = "10s", limitForPeriodMap = "{LiMing: 1, WangWu: 2}")
            @PathVariable String name) throws InterruptedException {
        Thread.sleep(1000 * 10);
        return name;
    }

}
