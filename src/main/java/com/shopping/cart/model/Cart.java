package com.shopping.cart.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart { // Singleton Pattern
    private List<Item> items = new ArrayList<>();
    private Double totalAmount = 0.0;
    private Integer appliedPromotionId = 0;
    private Double totalDiscount = 0.0;
    private static Cart instance = new Cart();
    private Cart() {
    }
    public static Cart getInstance() {
        return instance;
    }


}
