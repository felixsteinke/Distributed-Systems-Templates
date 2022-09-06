package com.mono.api.cart;

import com.mono.api.product.Product;
import lombok.Getter;

@Getter
public class CartItem {

    private final Product product;
    private final Integer count;

    public CartItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }
}
