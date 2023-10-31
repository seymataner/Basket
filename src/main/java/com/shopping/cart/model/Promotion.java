package com.shopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    private Integer promotionId = 0;
    private Double totalDiscount = 0.0;
    private Double totalDiscountedAmount = 0.0;
}
