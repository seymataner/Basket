package com.shopping.cart.exception;

public class MaxSameItemToVasItemQuantityException extends RuntimeException  {

    private static final String message = "Max vasItem should be 3 for same item";
    public MaxSameItemToVasItemQuantityException() {
        super(message);
    }
}
