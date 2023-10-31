package com.shopping.cart.dto.response;

import com.shopping.cart.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayCartResponse {

    private Boolean result;
    private Cart cart;

}
