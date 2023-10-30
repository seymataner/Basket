package com.shopping.cart.domain;


//{"command":"addVasItemToItem", "payload": {"itemId": int, "vasItemId":int, "vasCategoryId": int, "vasSellerId":int, "price":double, "quantity":int}}
public class VasItem {
    private Integer itemId;
    private Integer vasItemId;
    private Integer vasCategoryId;
    private Integer vasSellerId;
    private Double price;

}
