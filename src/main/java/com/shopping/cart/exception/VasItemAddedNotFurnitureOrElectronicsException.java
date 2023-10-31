package com.shopping.cart.exception;

import com.shopping.cart.utils.Constants;

public class VasItemAddedNotFurnitureOrElectronicsException extends RuntimeException  {

    private static final String message = "Vas Item can be added category Id " + Constants.FURNITURE_CATEGORY_ID + " or " + Constants.ELECTRONIC_CATEGORY_ID;
    public VasItemAddedNotFurnitureOrElectronicsException() {
        super(message);
    }
}
