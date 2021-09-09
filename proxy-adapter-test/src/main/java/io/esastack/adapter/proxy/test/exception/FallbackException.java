package io.esastack.adapter.proxy.test.exception;

public class FallbackException extends RuntimeException {

    public FallbackException() {
        super();
    }

    public FallbackException(String message) {
        super(message);
    }
}
