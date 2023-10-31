package com.shopping.cart.dto.request;

import lombok.Data;

// {"command":"addVasItemToItem", "payload": {"itemId": int, "vasItemId":int, "vasCategoryId": int, "vasSellerId":int, "price":double, "quantity":int}}
@Data
public class AddVasItemRequest {

    private Integer itemId;
    private Integer vasItemId;
    private Integer vasCategoryId;
    private Integer vasSellerId;
    private Double price;
    private Integer quantity;
}
