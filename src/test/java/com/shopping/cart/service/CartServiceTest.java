package com.shopping.cart.service;

import com.shopping.cart.converter.Converter;
import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.dto.request.RemoveItemRequest;
import com.shopping.cart.dto.response.*;
import com.shopping.cart.helper.TestHelper;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Item;
import com.shopping.cart.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private Cart cart;

    @Mock
    private Converter converter;

    @Mock
    private ValidationService validationService;

    @Mock
    private PromotionService promotionService;

    private static final int TEST_ITEM_ID = 1;


    public CartServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDisplayCart() {
        DisplayCartResponse displayCartResponse = cartService.displayCart();

        assertEquals(Constants.TRUE, displayCartResponse.getResult());
        assertEquals(cart, displayCartResponse.getCart());
    }

    @Test
    public void testAddItem() {

        AddItemRequest request = TestHelper.createValidItemRequest();
        assertDoesNotThrow(() -> validationService.validateAddItem(cart, request));

        Item newItem = TestHelper.createSampleItem3();
        Item existingItem = TestHelper.createSampleItem3();
        List<Item> itemList = new ArrayList<>();
        itemList.add(existingItem);

        when(converter.addItemRequestConvertToItem(request)).thenReturn(newItem);
        when(cart.getItems()).thenReturn(itemList);

        cartService.addItem(request);

        assertEquals(cart.getItems().size(), 1);

        when(cart.getItems()).thenReturn(new ArrayList<>());

        cartService.addItem(request);

        assertEquals(cart.getItems().size(), 1);

        AddItemResponse response = cartService.addItem(request);

        assertTrue(response.getResult() == Constants.TRUE);
        assertEquals(response.getMessage(), Constants.ADD_ITEM_MESSAGE);


    }

    @Test
    public void testVasAddItem() {
        AddVasItemRequest request = TestHelper.createValidVasItemRequestForAddVasItem();
        Item existingItem = TestHelper.createSampleItem3();
        List<Item> itemList = new ArrayList<>();
        itemList.add(existingItem);
        when(cart.getItems()).thenReturn(itemList);
        AddVasItemResponse response = cartService.addVasItem(request);

        assertTrue(response.getResult() == Constants.TRUE);
        assertEquals(response.getMessage(), Constants.ADD_ITEM_MESSAGE);


    }

    @Test
    public void testResetCart() {

        ResetCartResponse response = cartService.resetCart();

        assertEquals(Constants.TRUE, response.getResult());
        assertEquals(Constants.RESET_CART_MESSAGE, response.getMessage());
    }

    @Test
    public void testRemoveItem() {
        Item item = TestHelper.createSampleItem3();
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setItems(itemList);

        when(cart.getItems()).thenReturn(itemList);

        RemoveItemRequest request = new RemoveItemRequest();
        request.setItemId(TEST_ITEM_ID);


        RemoveItemResponse removeItemResponse = cartService.removeItem(request);
        assertEquals(Constants.TRUE, removeItemResponse.getResult());
        assertEquals(Constants.REMOVE_ITEM_MESSAGE, removeItemResponse.getMessage());
    }


}
