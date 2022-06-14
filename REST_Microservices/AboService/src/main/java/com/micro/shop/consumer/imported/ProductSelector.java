package com.micro.shop.consumer.imported;

import java.util.List;

public interface ProductSelector {
    List<Product> getAllProducts();

    Product getProduct(Integer productNr);
}
