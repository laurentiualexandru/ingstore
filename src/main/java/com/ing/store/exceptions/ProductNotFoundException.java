package com.ing.store.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super();
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
    public ProductNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
