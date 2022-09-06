package com.micro.api.cart;

import com.micro.api.external.Product;
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
