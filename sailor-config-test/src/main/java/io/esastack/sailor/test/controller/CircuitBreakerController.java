package io.esastack.sailor.test.controller;


import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/circuitBreaker")
public class CircuitBreakerController {

    @GetMapping("/withoutFallback")
    @ResponseBody
    public String withoutFallback() throws InterruptedException {
        Thread.sleep(100);
        return "withoutFallback";
    }

}
