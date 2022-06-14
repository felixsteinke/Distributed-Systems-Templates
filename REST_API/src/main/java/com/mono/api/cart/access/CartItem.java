package com.mono.api.cart.access;

import com.mono.api.product.access.Product;

public class CartItem {

    private final Product product;
    private final Integer count;

    public CartItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }
}
