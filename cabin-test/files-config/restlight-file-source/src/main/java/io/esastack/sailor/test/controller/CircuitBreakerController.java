package io.esastack.sailor.test.controller;


import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.GetMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.RequestMapping;
import esa.restlight.spring.shaded.org.springframework.web.bind.annotation.ResponseBody;
import io.esastack.servicekeeper.core.annotation.CircuitBreaker;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/circuitBreaker")
public class CircuitBreakerController {

    @GetMapping("/withoutFallback")
    @ResponseBody
    @CircuitBreaker(ringBufferSizeInClosedState = 4,
            ringBufferSizeInHalfOpenState = 2)
    public String withoutFallback() throws InterruptedException {
        Thread.sleep(100);
        return "withoutFallback";
    }

}
