package com.mono.api.cart.internal;

import com.mono.api.abo.AboService;
import com.mono.api.cart.CartItem;
import com.mono.api.product.Product;
import com.mono.api.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CartManagement {

    private final List<CartItem> CART = new ArrayList<>();
    private final AboService aboService;
    private final ProductService productService;

    @Autowired
    public CartManagement(AboService aboService, ProductService productService) {
        this.aboService = aboService;
        this.productService = productService;
    }

    public List<CartItem> getCart() {
        return this.CART;
    }

    public void addToCart(Integer productNr) {
        Product input = productService.getProduct(productNr);
        CART.add(new CartItem(input, 1)); // count as placeholder for more flexibility
    }

    public void checkoutCart() {
        CART.forEach(item -> aboService.addAbo(item.getProduct().getNr()));
        this.resetCart();
    }

    public void resetCart() {
        CART.clear();
    }
}
