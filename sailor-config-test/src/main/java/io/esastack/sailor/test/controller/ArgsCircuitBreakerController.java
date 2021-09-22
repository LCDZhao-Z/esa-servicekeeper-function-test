package io.esastack.sailor.test.controller;

import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.PathVariable;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/argCircuitBreaker")
public class ArgsCircuitBreakerController {

    @GetMapping("/withoutFallback/{name}")
    @ResponseBody
    public String withoutFallback(
            @PathVariable String name) throws InterruptedException {
        Thread.sleep(100);
        return name;
    }

}
