package com.shopping.cart.exception;

public class ValidationException extends RuntimeException {
    private static final String message = "Fields must not be null";
    public ValidationException() {
        super(message);
    }
}
