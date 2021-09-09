package io.esastack.sailor.test.controller;

import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import io.esastack.servicekeeper.core.annotation.RateLimiter;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/rateLimit")
public class RateLimitController {

    @GetMapping("/withoutFallback")
    @ResponseBody
    @RateLimiter(value = 2, limitRefreshPeriod = "10s")
    public String withoutFallback() throws InterruptedException {
        Thread.sleep(1000 * 10);
        return "withoutFallback";
    }

}
