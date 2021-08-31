package io.esastack.adapter.springboot.test.fallback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class Fallback {

    public String fallbackMethod() {
        return "fallbackMethod";
    }

    public CompletionStage<String> asyncFallbackMethod() {
        return CompletableFuture.completedFuture("fallbackMethod");
    }
}
