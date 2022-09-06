package com.micro.api.cart.internal;

import com.micro.api.cart.CartItem;
import com.micro.api.external.IAboService;
import com.micro.api.external.IProductService;
import com.micro.api.external.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CartManagement {

    private final List<CartItem> CART = new ArrayList<>();
    private final IAboService aboService;
    private final IProductService productService;

    @Autowired
    public CartManagement(IAboService aboService, IProductService productService) {
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
