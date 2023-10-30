package com.shopping.cart.controller;


import com.shopping.cart.dto.request.AddItemRequest;
import com.shopping.cart.dto.request.RemoveItemRequest;
import com.shopping.cart.dto.response.AddItemResponse;
import com.shopping.cart.dto.response.DisplayCartResponse;
import com.shopping.cart.dto.response.RemoveItemResponse;
import com.shopping.cart.dto.response.ResetCartResponse;
import com.shopping.cart.exception.BusinessException;
import com.shopping.cart.service.CartService;
import com.shopping.cart.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cart", produces = "application/json")

public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService)throws BusinessException {
        this.cartService = cartService;
    }

    @GetMapping("/display-cart")
    public ResponseEntity<DisplayCartResponse> displayCart()throws BusinessException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartService.displayCart());
    }

    @PostMapping("/add-item")
    public ResponseEntity<AddItemResponse> addItemToCart(@RequestBody AddItemRequest request) throws BusinessException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartService.addItem(request));
    }

    @DeleteMapping("/remove-item/{itemId}")
    public ResponseEntity<RemoveItemResponse> removeItemFromCart(@PathVariable Integer itemId) throws BusinessException {
        RemoveItemRequest request = new RemoveItemRequest();
        request.setItemId(itemId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartService.removeItem(request));
    }

    @PostMapping("/reset-cart")
    public ResponseEntity<ResetCartResponse> resetCart() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartService.resetCart());
    }
}
