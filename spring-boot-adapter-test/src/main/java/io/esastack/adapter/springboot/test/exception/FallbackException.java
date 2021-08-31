package io.esastack.adapter.springboot.test.exception;

public class FallbackException extends RuntimeException{

    public FallbackException() {
        super();
    }

    public FallbackException(String message) {
        super(message);
    }
}
