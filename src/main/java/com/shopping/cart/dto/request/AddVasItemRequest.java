package com.shopping.cart.dto.request;

import lombok.Data;

@Data
public class AddVasItemRequest {

    private Integer itemId;
    private Integer vasItemId;
    private Integer vasCategoryId;
    private Integer vasSellerId;
    private Double price;
    private Integer quantity;

}
