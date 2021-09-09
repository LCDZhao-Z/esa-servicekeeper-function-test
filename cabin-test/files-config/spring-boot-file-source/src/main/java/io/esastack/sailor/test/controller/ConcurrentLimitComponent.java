package io.esastack.sailor.test.controller;

import io.esastack.servicekeeper.core.annotation.ConcurrentLimiter;
import org.springframework.stereotype.Component;

@Component
public class ConcurrentLimitComponent {

    @ConcurrentLimiter(2)
    public String withoutFallback() throws InterruptedException {
        Thread.sleep(20 * 1000);
        return "withoutFallback";
    }

}
