package com.shopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VasItem {
    private Integer itemId;
    private Integer vasItemId;
    private Integer vasCategoryId;
    private Integer vasSellerId;
    private Double price;
    private Integer quantity;

}
