package com.shopping.cart.dto.request;
import lombok.Data;


// {"command":"addItem","payload":{"itemId":int,"categoryId":int,"sellerId":int,"price":double,"quantity":int}}
@Data
public class AddItemRequest {

    private Integer itemId;
    private Integer categoryId;
    private Integer sellerId;
    private Double price;

    private Integer quantity;
}
