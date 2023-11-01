package com.shopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
public class Cart {
    private List<Item> items = new ArrayList<>();
    private Double totalAmount = 0.0;
    private Integer appliedPromotionId = 0;
    private Double totalDiscount = 0.0;

}
