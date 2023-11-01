package com.shopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private List<Item> items = new ArrayList<>();
    private Double totalAmount = 0.0;
    private Integer appliedPromotionId = 0;
    private Double totalDiscount = 0.0;

}
