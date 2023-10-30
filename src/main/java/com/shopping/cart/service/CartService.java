package com.shopping.cart.service;

import com.shopping.cart.Converter.Converter;
import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.RemoveItemRequest;
import com.shopping.cart.dto.response.AddItemResponse;
import com.shopping.cart.dto.response.DisplayCartResponse;
import com.shopping.cart.dto.response.RemoveItemResponse;
import com.shopping.cart.dto.response.ResetCartResponse;
import com.shopping.cart.exception.BusinessException;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Item;
import com.shopping.cart.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartService {
    private static Logger logger = LoggerFactory.getLogger(CartService.class);

    private Cart cart = Cart.getInstance();
    @Autowired
    private Converter itemConverter;

    public DisplayCartResponse displayCart() {
        DisplayCartResponse response = new DisplayCartResponse();
        response.setResult(Constants.SUCCESS_TRUE);
        response.setItems(cart.getItems());

        return response;
    }

    public AddItemResponse addItem(AddItemRequest request) throws BusinessException {
        AddItemResponse response = new AddItemResponse();
        cart.addItem(itemConverter.addItemRequestConvertToItem(request));
        checkMaxUniqueItems(cart);

        response.setResult(Constants.SUCCESS_TRUE);
        response.setMessage(Constants.ADD_ITEM_MESSAGE);

        return response;
    }

    public RemoveItemResponse removeItem(RemoveItemRequest request) throws BusinessException {
        RemoveItemResponse response = new RemoveItemResponse();
        Item itemToRemove = cart.getItems().stream()
                .filter(item -> item.getItemId().equals(request.getItemId()))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Item not found."));

        cart.getItems().remove(itemToRemove);
        response.setResult(Constants.SUCCESS_TRUE);
        response.setMessage(Constants.REMOVE_ITEM_MESSAGE);

        return response;
    }

    public ResetCartResponse resetCart() {
        ResetCartResponse response = new ResetCartResponse();
        cart.getItems().clear();
        response.setResult(Constants.SUCCESS_TRUE);
        response.setMessage(Constants.RESET_CART_MESSAGE);

        return response;
    }

    public void checkMaxUniqueItems(Cart cart) throws BusinessException {  // Check if the cart contains the maximum allowed unique items (10)
        Set<Integer> uniqueItemIds = new HashSet<>();
        for (Item item : cart.getItems()) {
            uniqueItemIds.add(item.getItemId());
        }

        if (uniqueItemIds.size() >= 10) {
            throw new BusinessException("Cart is full. You cannot add more unique items.");
        }
    }


}
