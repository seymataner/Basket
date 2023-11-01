package com.shopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Integer itemId;
    private Integer categoryId;
    private Integer sellerId;
    private Double price;
    private Integer quantity;
    private List<VasItem> vasItems;

}
