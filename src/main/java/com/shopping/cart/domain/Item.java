package com.shopping.cart.domain;


//{"command":"addItem","payload":{"itemId":int,"categoryId":int,"sellerId":int,"price":double,"quantity":int}}
public class Item {
    private Integer itemId;
    private Integer categoryId;
    private Integer sellerId;
    private Double price;
}
