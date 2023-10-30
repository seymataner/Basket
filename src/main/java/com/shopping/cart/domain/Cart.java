package com.shopping.cart.domain;


import lombok.AllArgsConstructor;

import java.util.List;

/*
   {"result":boolean, "message":{"items":[ty.item], "totalAmount":double, "appliedPromotionId":int, "totalDiscount":double}}
    ty.item -> {"itemId": int, "categoryId": int, "sellerId":int, "price":double, "quantity":int, "vasItems":[ty.vasItem]}
    ty.vasItem -> {"vasItemId":int, "vasCategoryId": int, "vasSellerId":int, "price":double, "quantity":int}
 */
@AllArgsConstructor
public class Cart {
    private List<Item> items;
    private Double totalAmount;
    private Integer appliedPromotionId;
    private Double totalDiscount;


}
