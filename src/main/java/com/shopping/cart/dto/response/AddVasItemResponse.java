package com.shopping.cart.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddVasItemResponse {
    private Boolean result;
    private String message;
}
