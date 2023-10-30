package com.shopping.cart.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<Item> items = new ArrayList<>();
    private static Cart instance = new Cart();
    private Cart() {
    }
    public static Cart getInstance() {
        return instance;
    }
    public void addItem(Item item) {
        items.add(item);
    }
}
