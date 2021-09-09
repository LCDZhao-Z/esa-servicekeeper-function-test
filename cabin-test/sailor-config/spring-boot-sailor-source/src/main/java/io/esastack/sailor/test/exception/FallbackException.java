package io.esastack.sailor.test.exception;

public class FallbackException extends RuntimeException {

    public FallbackException() {
        super();
    }

    public FallbackException(String message) {
        super(message);
    }
}
