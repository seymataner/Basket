package com.shopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

// {"command":"addItem","payload":{"itemId":int,"categoryId":int,"sellerId":int,"price":double,"quantity":int}}
@Data
@AllArgsConstructor
public class Item {
    private Integer itemId;
    private Integer categoryId;
    private Integer sellerId;
    private Double price;
    private Integer quantity;
    private List<VasItem> vasItems;

}
