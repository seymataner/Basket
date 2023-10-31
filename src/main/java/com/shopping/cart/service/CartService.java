package com.shopping.cart.service;

import com.shopping.cart.Converter.Converter;
import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.dto.request.RemoveItemRequest;
import com.shopping.cart.dto.response.*;
import com.shopping.cart.exception.ItemNotFoundException;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Item;
import com.shopping.cart.model.VasItem;
import com.shopping.cart.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CartService {

    private final Cart cart = Cart.getInstance();
    private final Converter converter;

    private final ValidationService validationService;
    private final PromotionService promotionService;

    @Autowired
    public CartService(Converter converter, ValidationService validationService, PromotionService promotionService) {
        this.converter = converter;
        this.validationService = validationService;
        this.promotionService = promotionService;
    }

    public DisplayCartResponse displayCart() {
        DisplayCartResponse response = new DisplayCartResponse();
        response.setResult(Constants.TRUE);
        response.setItems(cart.getItems());

        return response;
    }

    public AddItemResponse addItem(AddItemRequest request) {
        validationService.validateAddItem(cart, request);

        Item existingItem = findItemByItemId(request.getItemId());

        if (existingItem != null) {

            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
        } else {
            cart.getItems().add(converter.addItemRequestConvertToItem(request));
        }
        promotionService.calculateCart(cart);


        AddItemResponse response = new AddItemResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.ADD_ITEM_MESSAGE);

        return response;
    }

    public AddVasItemResponse addVasItem(AddVasItemRequest request) throws ItemNotFoundException {
        Item item = findItemByItemId(request.getItemId());

        if (item != null) {
            VasItem vasItem = findVasItemByVasItemId(item, request.getVasItemId());
            validationService.validateAddVasItem(cart, item, vasItem, request);

            if (vasItem != null) {
                vasItem.setQuantity(vasItem.getQuantity() + request.getQuantity());
            } else {
                item.getVasItems().add(converter.addVasItemRequestConvertToVasItem(request));
            }
        } else {
            throw new ItemNotFoundException();
        }

        promotionService.calculateCart(cart);

        AddVasItemResponse response = new AddVasItemResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.ADD_ITEM_MESSAGE);

        return response;
    }

    public RemoveItemResponse removeItem(RemoveItemRequest request) throws ItemNotFoundException {

        Item itemToRemove = cart.getItems().stream()
                .filter(item -> item.getItemId().equals(request.getItemId()))
                .findFirst().
                orElseThrow(ItemNotFoundException::new);

        cart.getItems().remove(itemToRemove);
        promotionService.calculateCart(cart);

        RemoveItemResponse response = new RemoveItemResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.REMOVE_ITEM_MESSAGE);

        return response;
    }

    public ResetCartResponse resetCart() {
        cart.getItems().clear();
        promotionService.calculateCart(cart);

        ResetCartResponse response = new ResetCartResponse();
        response.setResult(Constants.TRUE);
        response.setMessage(Constants.RESET_CART_MESSAGE);

        return response;
    }


    public Item findItemByItemId(Integer itemId) {

        return cart.getItems().stream().filter(item ->
                Objects.equals(item.getItemId(), itemId)).findFirst().orElse(null);
    }

    private VasItem findVasItemByVasItemId(Item item, Integer vasItemId) {
        return item.getVasItems().stream().filter(vasItem ->
                Objects.equals(vasItem.getVasItemId(), vasItemId)).findFirst().orElse(null);
    }


}
