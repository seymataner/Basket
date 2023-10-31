package com.shopping.cart.exception;

public class VasItemPriceException extends RuntimeException  {

    private static final String message = "Vas item price should be smaller than item";
    public VasItemPriceException() {
        super(message);
    }
}
