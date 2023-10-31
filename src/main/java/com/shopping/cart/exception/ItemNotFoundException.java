package com.shopping.cart.exception;

public class ItemNotFoundException extends RuntimeException {

    private static final String message = "Item not found";

    public ItemNotFoundException() {
        super(message);
    }

}
