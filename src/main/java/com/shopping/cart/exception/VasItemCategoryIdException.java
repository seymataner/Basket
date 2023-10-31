package com.shopping.cart.exception;

import com.shopping.cart.utils.Constants;

public class VasItemCategoryIdException extends RuntimeException  {

    private static final String message = "Vas Item category Id should be " + Constants.VAS_ITEM_CATEGORY_ID;
    public VasItemCategoryIdException() {
        super(message);
    }
}
