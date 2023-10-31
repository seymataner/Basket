package com.shopping.cart.exception;

public class MaxSameItemQuantityException extends RuntimeException  {

    private static final String message = "Same item quantity should be max 10.";
    public MaxSameItemQuantityException() {
        super(message);
    }
}
