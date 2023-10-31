package com.shopping.cart.dto.request;
import lombok.Data;

import javax.validation.constraints.NotNull;


// {"command":"addItem","payload":{"itemId":int,"categoryId":int,"sellerId":int,"price":double,"quantity":int}}
@Data
public class AddItemRequest {

    @NotNull
    private Integer itemId;

    @NotNull
    private Integer categoryId;

    @NotNull
    private Integer sellerId;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;
}
