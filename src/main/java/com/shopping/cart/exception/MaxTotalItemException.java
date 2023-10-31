package com.shopping.cart.exception;

public class MaxTotalItemException extends RuntimeException  {

    private static final String message = "Cart can contain a maximum of 30 items";
    public MaxTotalItemException() {
        super(message);
    }
}
