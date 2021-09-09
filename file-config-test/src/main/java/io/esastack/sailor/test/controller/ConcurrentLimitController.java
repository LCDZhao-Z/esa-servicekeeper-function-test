package io.esastack.sailor.test.controller;

import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import io.esastack.servicekeeper.core.annotation.ConcurrentLimiter;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/concurrentLimit/")
public class ConcurrentLimitController {

    @GetMapping("/withoutFallback")
    @ResponseBody
    @ConcurrentLimiter(2)
    public String withoutFallback() throws InterruptedException {
        Thread.sleep(20 * 1000);
        return "withoutFallback";
    }

}
