package com.shopping.cart.exception;

import com.shopping.cart.utils.Constants;

public class VasItemSellerIdException extends RuntimeException  {

    private static final String message = "Vas Item seller Id should be " + Constants.VAS_ITEM_SELLER_ID;
    public VasItemSellerIdException() {
        super(message);
    }
}
