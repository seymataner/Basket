package com.shopping.cart.service;

import com.shopping.cart.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class PromotionService {

    public void calculateCart(Cart cart) {

    }

    private Double calculateSameSellerPromotion(Cart cart) {
        return 0.0;
    }

    private Double calculateCategoryPromotion (Cart cart) {
        return 0.0;
    }

    private Double calculateTotalPromotion (Cart cart) {
        return 0.0;
    }

    private Double findMostAdvantageousPromotion() {

        return 0.0;
    }

}
