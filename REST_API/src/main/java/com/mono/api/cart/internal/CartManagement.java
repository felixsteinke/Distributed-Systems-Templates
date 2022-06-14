package com.mono.api.cart.internal;

import com.mono.api.abo.access.AboCreator;
import com.mono.api.cart.access.CartItem;
import com.mono.api.product.access.ProductSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartManagement {

    private final List<CartItem> CART = new ArrayList<>();
    private final AboCreator aboCreator;
    private final ProductSelector productSelector;

    @Autowired
    public CartManagement(AboCreator aboCreator, ProductSelector productSelector) {
        this.aboCreator = aboCreator;
        this.productSelector = productSelector;
    }

    public List<CartItem> getCart() {
        return this.CART;
    }

    public void addToCart(Integer productNr) {
        CART.add(new CartItem(productSelector.getProduct(productNr), 1));
    }

    public void checkoutCart() {
        CART.forEach(item -> aboCreator.addAbo(item.getProduct().getNr()));
        this.resetCart();
    }

    public void resetCart() {
        CART.clear();
    }
}
