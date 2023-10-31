package com.shopping.cart.exception;

public class MaxSizeExceededException extends RuntimeException  {

    private static final String message = "Cart can contain a maximum of 10 unique items";
    public MaxSizeExceededException() {
        super(message);
    }
}
