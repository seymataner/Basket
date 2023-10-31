package com.shopping.cart.exception;

public class MaxSameDigitalItemQuantityException extends RuntimeException  {

    private static final String message = "Same digital item quantity should be max 5.";
    public MaxSameDigitalItemQuantityException() {
        super(message);
    }
}
