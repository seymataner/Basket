package com.shopping.cart.exception;

public class MaxTotalItemQuantityException extends RuntimeException  {

    private static final String message = "Cart can contain a maximum of 30 items";
    public MaxTotalItemQuantityException() {
        super(message);
    }
}
