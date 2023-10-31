package com.shopping.cart.exception;

import com.shopping.cart.utils.Constants;

public class MaxTotalPriceException extends RuntimeException  {

    private static final String message = "Total price can not be higher than " + Constants.MAX_TOTAL_PRICE;
    public MaxTotalPriceException() {
        super(message);
    }
}
