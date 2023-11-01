package com.shopping.cart.service;

import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.exception.*;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Item;
import com.shopping.cart.model.VasItem;
import com.shopping.cart.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class ValidationService {

    public void validateAddItem(Cart cart, AddItemRequest request) {
        checkMaxSameDefaultItemQuantity(cart, request.getItemId(), request.getCategoryId(), request.getQuantity());
        checkMaxSameDigitalItemQuantity(cart, request.getItemId(), request.getCategoryId(), request.getQuantity());
        checkMaxUniqueItemQuantity(cart, request.getItemId());
        checkMaxTotalItemQuantity(cart, request.getQuantity());
        checkItemSellerIdNotAllowed(request.getSellerId());

    }

    public void validateAddVasItem(Cart cart, Item item, VasItem vasItem, AddVasItemRequest request) {
        checkVasItemSellerId(request.getVasSellerId());
        checkVasItemCategoryId(request.getVasCategoryId());
        checkMaxTotalItemQuantity(cart, request.getQuantity());
        checkVasItemNotAllowedCategory(item.getCategoryId());
        checkMaxSameItemToVasItemQuantity(vasItem, request.getQuantity());
        checkVasItemPrice(item.getPrice(), request.getPrice());


    }


    //** ADD ITEM VALIDATION METHODS
    // Check if the cart contains the maximum allowed same default items (10)
    private void checkMaxSameDefaultItemQuantity(Cart cart, Integer itemId, Integer categoryId, Integer quantity) {
        Item item = findItemByItemId(cart, itemId);
        int totalQuantity = item != null ? item.getQuantity() + quantity : quantity;

        if (totalQuantity > 10 && (!Objects.equals(categoryId, Constants.DIGITAL_CATEGORY_ID)))
            throw new MaxSameDefaultItemQuantityException();
    }

    // Check if the cart contains the maximum allowed same digital items (5)
    private void checkMaxSameDigitalItemQuantity(Cart cart, Integer itemId, Integer categoryId, Integer quantity) {
        Item item = findItemByItemId(cart, itemId);
        int totalQuantity = item != null ? item.getQuantity() + quantity : quantity;

        if (totalQuantity > 5 && (Objects.equals(categoryId, Constants.DIGITAL_CATEGORY_ID)))
            throw new MaxSameDigitalItemQuantityException();
    }

    // Check if the cart contains the maximum allowed unique items (10) exclude: vasItem
    private void checkMaxUniqueItemQuantity(Cart cart, Integer itemId) {
        Set<Integer> uniqueItemIds = new HashSet<>();
        for (Item item : cart.getItems()) {
            uniqueItemIds.add(item.getItemId());
        }
        uniqueItemIds.add(itemId);
        if (uniqueItemIds.size() > 10)
            throw new MaxUniqueItemQuantityException();

    }

    // Check vasItem sellerId -> 5003
    private void checkItemSellerIdNotAllowed(Integer vasSellerId) {
        if (Objects.equals(vasSellerId, Constants.VAS_ITEM_SELLER_ID))
            throw new ItemSellerIdNotAllowedException();
    }


    //** ADD VAS ITEM VALIDATION METHODS
    private void checkVasItemSellerId(Integer vasSellerId) {
        if (!Objects.equals(vasSellerId, Constants.VAS_ITEM_SELLER_ID))
            throw new VasItemSellerIdException();
    }


    // Check max 3 vasItem to same item
    private void checkMaxSameItemToVasItemQuantity(VasItem vasItem, Integer quantity) {
        int vasItemQuantity = (vasItem != null) ? vasItem.getQuantity() + quantity : quantity;
        if (vasItemQuantity > 3)
            throw new MaxSameItemToVasItemQuantityException();
    }

    // Check max 3 vasItem to same item
    private void checkVasItemPrice(Double itemPrice, Double vasItemPrice) {
        if (itemPrice < vasItemPrice)
            throw new VasItemPriceException();
    }


    // Check vasItem categoryId  -> 3242
    private void checkVasItemCategoryId(Integer vasCategoryId) {
        if (!Objects.equals(vasCategoryId, Constants.VAS_ITEM_CATEGORY_ID))
            throw new VasItemCategoryIdException();
    }

    private void checkVasItemNotAllowedCategory(Integer categoryId) {
        if (!Objects.equals(categoryId, Constants.ELECTRONIC_CATEGORY_ID)
                && !Objects.equals(categoryId, Constants.FURNITURE_CATEGORY_ID)) {
            throw new VasItemNotAllowedCategoryException();
        }
    }


    // //** COMMON VALIDATION METHODS
    // Check if the cart contains the maximum allowed items (30) include :vasItem
    private void checkMaxTotalItemQuantity(Cart cart, Integer quantity) {

        int totalQuantity = cart.getItems().stream().mapToInt(item ->
                item.getQuantity() + item.getVasItems().stream().mapToInt(VasItem::getQuantity).sum()).sum();

        if (totalQuantity + quantity > 30)
            throw new MaxTotalItemQuantityException();
    }


    private Item findItemByItemId(Cart cart, Integer itemId) {

        return cart.getItems().stream().filter(item ->
                Objects.equals(item.getItemId(), itemId)).findFirst().orElse(null);
    }


}
