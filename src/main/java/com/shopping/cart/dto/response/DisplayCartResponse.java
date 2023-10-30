package com.shopping.cart.dto.response;

import com.shopping.cart.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayCartResponse {

    private Boolean result;
    private List<Item> items;
    private Double totalAmount = 0.0;
    private Integer appliedPromotionId = 0;
    private Double totalDiscount = 0.0;

}
