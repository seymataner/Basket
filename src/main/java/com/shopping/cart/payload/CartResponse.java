package com.shopping.cart.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartResponse {
    private Boolean result;
    private String message;
}
