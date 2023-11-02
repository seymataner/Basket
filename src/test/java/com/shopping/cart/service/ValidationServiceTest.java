package com.shopping.cart.service;

import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.exception.*;
import com.shopping.cart.helper.TestHelper;
import com.shopping.cart.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();

    private Cart cart;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = TestHelper.createSampleCart();
    }

    @Test
    public void testValidateAddItemNoException() {

        AddItemRequest request = TestHelper.createValidItemRequest();
        assertDoesNotThrow(() -> validationService.validateAddItem(cart, request));
    }

    @Test
    public void testCheckMaxSameDefaultItemQuantity() {
        AddItemRequest request = TestHelper.createNotValidQuantityDefaultItemRequest();

        assertThrows(MaxSameDefaultItemQuantityException.class, () -> validationService.validateAddItem(cart, request));
    }

    @Test
    public void testCheckMaxUniqueItemQuantity() {
        cart.setItems(TestHelper.createUniqueItems());
        AddItemRequest request = TestHelper.createNotAllowedTotalQuantityItemRequest();

        assertThrows(MaxUniqueItemQuantityException.class, () -> validationService.validateAddItem(cart, request));
    }

    @Test
    public void testCheckMaxTotalItemQuantity() {
        cart.setItems(TestHelper.createMaxItems());
        AddItemRequest request = TestHelper.createValidItemRequest();

        assertThrows(MaxTotalItemQuantityException.class, () -> validationService.validateAddItem(cart, request));
    }


    @Test
    public void testCheckMaxSameDigitalItemQuantity() {
        AddItemRequest request = TestHelper.createNotValidQuantityDigitalItemRequest();

        assertThrows(MaxSameDigitalItemQuantityException.class, () -> validationService.validateAddItem(cart, request));
    }

    @Test
    public void testCheckItemSellerIdNotAllowed() {
        AddItemRequest request = TestHelper.createNotValidSellerIdItemRequest();

        assertThrows(ItemSellerIdNotAllowedException.class, () -> validationService.validateAddItem(cart, request));
    }

    @Test
    public void testCheckVasItemSellerId() {
        cart.getItems().add(TestHelper.createSampleItem());
        AddVasItemRequest request = TestHelper.createNotValidSellerIdVasItemRequest();

        assertThrows(VasItemSellerIdException.class, () -> validationService.validateAddVasItem(cart, TestHelper.createSampleItem(), null, request));
    }

    @Test
    public void testCheckMaxSameItemToVasItemQuantity() {
        cart.getItems().add(TestHelper.createSampleItem());
        AddVasItemRequest request = TestHelper.createNotValidQuantityVasItemRequest();

        assertThrows(MaxSameItemToVasItemQuantityException.class, () -> validationService.validateAddVasItem(cart, TestHelper.createSampleItem(), null, request));

    }

    @Test
    public void testCheckVasItemPrice() {
        cart.getItems().add(TestHelper.createSampleItem());
        AddVasItemRequest request = TestHelper.createHighPriceVasItemRequest();

        assertThrows(VasItemPriceException.class, () -> validationService.validateAddVasItem(cart, TestHelper.createSampleItem(), null, request));

    }

    @Test
    public void testCheckVasItemCategoryId() {
        cart.getItems().add(TestHelper.createSampleItem());
        AddVasItemRequest request = TestHelper.createNotValidCategoryIdVasItemRequest();

        assertThrows(VasItemCategoryIdException.class, () -> validationService.validateAddVasItem(cart, TestHelper.createSampleItem(), null, request));

    }

    @Test
    public void testCheckVasItemNotAllowedCategory() {
        cart.getItems().add(TestHelper.createSampleItem3());
        AddVasItemRequest request = TestHelper.createValidVasItemRequest();

        assertThrows(VasItemNotAllowedCategoryException.class, () -> validationService.validateAddVasItem(cart, TestHelper.createSampleItem3(), null, request));

    }
}
