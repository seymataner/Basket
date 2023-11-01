package com.shopping.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.AddVasItemRequest;
import com.shopping.cart.dto.request.RemoveItemRequest;
import com.shopping.cart.dto.response.*;
import com.shopping.cart.helper.TestHelper;
import com.shopping.cart.service.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CartController.class)
public class CartControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    public void testDisplayCart() throws Exception {

        DisplayCartResponse mockResponse = new DisplayCartResponse();
        Mockito.when(cartService.displayCart()).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cart/display-cart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddItem() throws Exception {

        AddItemRequest mockRequest = TestHelper.createValidItemRequest();
        AddItemResponse mockResponse = new AddItemResponse();
        Mockito.when(cartService.addItem(mockRequest)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/add-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testVasAddItem() throws Exception {

        AddVasItemRequest mockRequest = TestHelper.createValidVasItemRequest();
        AddVasItemResponse mockResponse = new AddVasItemResponse();
        Mockito.when(cartService.addVasItem(mockRequest)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/add-vas-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRemoveItem() throws Exception {

        RemoveItemRequest mockRequest = new RemoveItemRequest();
        mockRequest.setItemId(1234);
        RemoveItemResponse mockResponse = new RemoveItemResponse();
        Mockito.when(cartService.removeItem(mockRequest)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/remove-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testResetCart() throws Exception {

        ResetCartResponse mockResponse = new ResetCartResponse();
        Mockito.when(cartService.resetCart()).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/reset-cart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
