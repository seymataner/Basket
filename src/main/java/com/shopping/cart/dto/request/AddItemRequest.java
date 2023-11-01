package com.shopping.cart.dto.request;

import lombok.Data;

@Data
public class AddItemRequest {

    private Integer itemId;
    private Integer categoryId;
    private Integer sellerId;
    private Double price;
    private Integer quantity;
}
