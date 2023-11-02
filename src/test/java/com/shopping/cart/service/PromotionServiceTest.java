package com.shopping.cart.service;

import com.shopping.cart.exception.MaxTotalPriceException;
import com.shopping.cart.helper.TestHelper;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Item;
import com.shopping.cart.model.VasItem;
import com.shopping.cart.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PromotionServiceTest {

    @InjectMocks
    private PromotionService promotionService;

    @Mock
    private Cart cart;

    private static final double MAX_TOTAL_PRICE = 500_000.0;

    public PromotionServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateCartWithEmpty() {

        when(cart.getItems()).thenReturn(new ArrayList<>());

        promotionService.calculateCart();

        assertEquals(cart.getTotalAmount(), 0.0);
        assertEquals(cart.getAppliedPromotionId(), 0);
        assertEquals(cart.getTotalDiscount(), 0.0);
    }

    @Test
    public void testCalculateCartForTotalPromotion() {

        List<Item> itemList = TestHelper.createUniqueItems();

        when(cart.getItems()).thenReturn(itemList);
        promotionService.calculateCart();

    }

    @Test
    public void testCalculateCartForSameSellerPromotion() {

        List<Item> itemList = TestHelper.createSameCategoryIdItems();

        when(cart.getItems()).thenReturn(itemList);
        promotionService.calculateCart();
    }

    @Test
    public void testCheckTotalPriceAfterAddingItem() {
        Item item = TestHelper.createSampleHighPriceItem();

        when(cart.getTotalAmount()).thenReturn(MAX_TOTAL_PRICE+1);
        cart.setTotalAmount(Constants.MAX_TOTAL_PRICE + 1);
        assertThrows(MaxTotalPriceException.class, () -> promotionService.checkTotalPriceAfterAddingItem(item));

    }

    @Test
    public void TestCheckTotalPriceAfterAddingVasItem() {
        Item item = TestHelper.createSampleHighPriceItem();
        VasItem vasItem = TestHelper.createSampleHighPriceVasItem();

        when(cart.getTotalAmount()).thenReturn(MAX_TOTAL_PRICE+1);
        cart.setTotalAmount(Constants.MAX_TOTAL_PRICE + 1);
        assertThrows(MaxTotalPriceException.class, () -> promotionService.checkTotalPriceAfterAddingVasItem(item, vasItem));

    }
}
