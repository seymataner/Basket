package com.shopping.cart.Converter;

import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.model.Item;
import com.shopping.cart.model.VasItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Converter {

    public Item addItemRequestConvertToItem(AddItemRequest addItemRequest) {
        return new Item(
                addItemRequest.getItemId(),
                addItemRequest.getCategoryId(),
                addItemRequest.getSellerId(),
                addItemRequest.getPrice(),
                addItemRequest.getQuantity(),
                new ArrayList<>()
        );
    }

    public VasItem addVasItemRequestConvertToItem(AddVasItemRequest addVasItemRequest) {
        return null;
    }

}
