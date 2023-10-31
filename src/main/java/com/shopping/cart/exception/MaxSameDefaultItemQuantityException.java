package com.shopping.cart.exception;

public class MaxSameDefaultItemQuantityException extends RuntimeException  {

    private static final String message = "Same default item quantity should be max 10.";
    public MaxSameDefaultItemQuantityException() {
        super(message);
    }
}
