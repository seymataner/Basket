package com.shopping.cart.exception;

import com.shopping.cart.utils.Constants;

public class ItemSellerIdNotAllowedException extends RuntimeException  {

    private static final String message = "Vas item seller ID is not allowed: " + Constants.VAS_ITEM_SELLER_ID;
    public ItemSellerIdNotAllowedException() {
        super(message);
    }
}
