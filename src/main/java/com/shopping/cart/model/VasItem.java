package com.shopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

//{"command":"addVasItemToItem", "payload": {"itemId": int, "vasItemId":int, "vasCategoryId": int, "vasSellerId":int, "price":double, "quantity":int}}
@Data
@AllArgsConstructor
public class VasItem {
    private Integer itemId;
    private Integer vasItemId;
    private Integer vasCategoryId;
    private Integer vasSellerId;
    private Double price;

}
