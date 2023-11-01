package com.shopping.cart.service;

import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.exception.MaxSameDefaultItemQuantityException;
import com.shopping.cart.exception.MaxSameDigitalItemQuantityException;
import com.shopping.cart.exception.MaxTotalItemQuantityException;
import com.shopping.cart.exception.MaxUniqueItemQuantityException;
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

}
