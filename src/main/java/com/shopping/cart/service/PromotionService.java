package com.shopping.cart.service;

import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Item;
import com.shopping.cart.model.Promotion;
import com.shopping.cart.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PromotionService {

    private final Cart cart;

    @Autowired
    public PromotionService(Cart cart) {
        this.cart = cart;
    }


    public void calculateCart() {
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            cart.setTotalAmount(0.0);
            cart.setAppliedPromotionId(0);
            cart.setTotalDiscount(0.0);
        } else {
            List<Promotion> promotions = new ArrayList<>();
            promotions.add(calculateSameSellerPromotion());
            //promotions.add(calculateCategoryPromotion());
            promotions.add(calculateTotalPromotion());
            Promotion selectedPromotion = findMostAdvantageousPromotion(promotions);
            cart.setAppliedPromotionId(selectedPromotion.getPromotionId());
            cart.setTotalDiscount(selectedPromotion.getTotalDiscount());
            cart.setTotalAmount(selectedPromotion.getTotalDiscountedAmount());

        }
    }

    private Promotion calculateSameSellerPromotion() {
        Promotion promotion = new Promotion();
        promotion.setPromotionId(Constants.CATEGORY_PROMOTION_ID);
        Set<Integer> uniqueSellerIds = cart.getItems().stream()
                .map(Item::getSellerId)
                .collect(Collectors.toSet());
        if(uniqueSellerIds.size() == 1){
            promotion.setTotalDiscount(calculateDefaultTotalPrice()*0.1);
            promotion.setTotalDiscountedAmount(calculateDefaultTotalPrice()*0.9);
        }

      return promotion;
    }

    private Promotion calculateCategoryPromotion () {
        return new Promotion();
    }

    private Promotion calculateTotalPromotion () {
        Promotion promotion = new Promotion();
        promotion.setPromotionId(Constants.SAME_SELLER_PROMOTION_ID);
        double defaultAmount = calculateDefaultTotalPrice();
        if (defaultAmount >= 50_000) {
            promotion.setTotalDiscount(2_000.00);
        } else if (defaultAmount >= 10_000) {
            promotion.setTotalDiscount(1_000.00);
        } else if (defaultAmount >= 5_000) {
            promotion.setTotalDiscount(500.00);
        } else if (defaultAmount >= 500) {
            promotion.setTotalDiscount(250.00);
        } else {
            promotion.setTotalDiscount(0.00);
        }
        promotion.setTotalDiscountedAmount(defaultAmount - promotion.getTotalDiscount());

        return promotion;
    }

    private Promotion findMostAdvantageousPromotion(List<Promotion> promotions) {
        return promotions.stream()
                .min(Comparator.comparingDouble(Promotion::getTotalDiscountedAmount))
                .orElse(new Promotion());
    }

    private Double calculateDefaultTotalPrice() {
        return cart.getItems().stream()
                .mapToDouble(item -> {
                    double itemPrice = item.getPrice() * item.getQuantity();
                    double vasItemPrice = item.getVasItems().stream()
                            .mapToDouble(vasItem -> vasItem.getPrice() * vasItem.getQuantity())
                            .sum();
                    return itemPrice + vasItemPrice;
                })
                .sum();
    }

}
