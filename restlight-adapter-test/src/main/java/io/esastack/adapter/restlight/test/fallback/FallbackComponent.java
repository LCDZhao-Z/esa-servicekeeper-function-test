package io.esastack.adapter.restlight.test.fallback;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Component
public class FallbackComponent {
    public String fallbackMethod() {
        return "fallbackMethod";
    }

    public CompletionStage<String> asyncFallbackMethod() {
        return CompletableFuture.completedFuture("fallbackMethod");
    }
}
