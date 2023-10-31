package com.shopping.cart.service;

import com.shopping.cart.Converter.Converter;
import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.dto.request.RemoveItemRequest;
import com.shopping.cart.dto.response.*;
import com.shopping.cart.exception.ItemNotFoundException;
import com.shopping.cart.exception.MaxTotalItemException;
import com.shopping.cart.exception.MaxUniqueItemException;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Item;
import com.shopping.cart.model.VasItem;
import com.shopping.cart.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class CartService {

    private final Cart cart = Cart.getInstance();
    private final Converter converter;

    public CartService(Converter converter) {
        this.converter = converter;
    }

    public DisplayCartResponse displayCart() {
        DisplayCartResponse response = new DisplayCartResponse();
        response.setResult(Constants.TRUE);
        response.setItems(cart.getItems());

        return response;
    }

    public AddItemResponse addItem(AddItemRequest request){
        checkMaxUniqueItems(cart, request.getItemId());
        checkMaxTotalItems(cart, request.getQuantity());

        Item existingItem = findItemByItemId(request.getItemId());

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            cart.getItems().add(converter.addItemRequestConvertToItem(request));
        }

        AddItemResponse response = new AddItemResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.ADD_ITEM_MESSAGE);

        return response;
    }

    public AddVasItemResponse addVasItem(AddVasItemRequest request) throws ItemNotFoundException {

        checkMaxTotalItems(cart, request.getQuantity());
        Item item = findItemByItemId(request.getItemId());

        if (item != null) {
            VasItem existingVasItem = findVasItemByVasItemId(item, request.getVasItemId());

            if (existingVasItem != null) {
                existingVasItem.setQuantity(existingVasItem.getQuantity() + 1);
            } else {
                item.getVasItems().add(converter.addVasItemRequestConvertToVasItem(request));
            }
        } else {
            throw new ItemNotFoundException();
        }

        AddVasItemResponse response = new AddVasItemResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.ADD_ITEM_MESSAGE);

        return response;
    }

    public RemoveItemResponse removeItem(RemoveItemRequest request) throws ItemNotFoundException {

        Item itemToRemove = cart.getItems().stream()
                .filter(item -> item.getItemId().equals(request.getItemId()))
                .findFirst()
                .orElseThrow(ItemNotFoundException::new);

        cart.getItems().remove(itemToRemove);

        RemoveItemResponse response = new RemoveItemResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.REMOVE_ITEM_MESSAGE);

        return response;
    }

    public ResetCartResponse resetCart() {
        cart.getItems().clear();

        ResetCartResponse response = new ResetCartResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.RESET_CART_MESSAGE);

        return response;
    }

    public void checkMaxUniqueItems(Cart cart, Integer itemId) throws MaxUniqueItemException {  // Check if the cart contains the maximum allowed unique items (10) exclude: vasItem
        Set<Integer> uniqueItemIds = new HashSet<>();
        for (Item item : cart.getItems()) {
            uniqueItemIds.add(item.getItemId());
        }
        uniqueItemIds.add(itemId);
        if (uniqueItemIds.size() > 10) {
            throw new MaxUniqueItemException();
        }
    }

    public void checkMaxTotalItems(Cart cart, Integer quantity) throws MaxUniqueItemException { // Check if the cart contains the maximum allowed items (30) include :vasItem

        int totalQuantity = cart.getItems().stream()
                .mapToInt(i -> i.getQuantity() + i.getVasItems().size())
                .sum();

        if (totalQuantity + quantity > 30) {
            throw new MaxTotalItemException();
        }
    }

    public Item findItemByItemId(Integer itemId) { // Check if the cart contains the maximum allowed items (30) include :vasItem

        return cart.getItems().stream()
                .filter(item -> Objects.equals(item.getItemId(), itemId))
                .findFirst()
                .orElse(null);
    }

    private VasItem findVasItemByVasItemId(Item item, int vasItemId) {
        return item.getVasItems().stream()
                .filter(vasItem -> vasItem.getVasItemId() == vasItemId)
                .findFirst()
                .orElse(null);
    }


}
