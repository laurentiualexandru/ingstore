package com.ing.store.exceptions;

public class ResourceException extends RuntimeException {
    public ResourceException() {
        super();
    }
    public ResourceException(String message) {
        super(message);
    }
    public ResourceException(String message, Throwable ex) {
        super(message, ex);
    }
}
