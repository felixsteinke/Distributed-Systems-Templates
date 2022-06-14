package com.micro.shop.product.internal;

import com.micro.shop.product.access.Product;
import com.micro.shop.product.access.ProductSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductManagement implements ProductSelector {

    private final ProductRepo productRepo;

    @Autowired
    public ProductManagement(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product) {
        return new Product(productRepo.save(new ProductEntity(product)));
    }

    public void deleteProduct(Integer productNr) {
        productRepo.deleteById(productNr);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll().stream().map(Product::new).collect(Collectors.toList());
    }

    @Override
    public Product getProduct(Integer productNr) {
        return new Product(productRepo.findById(productNr).orElseThrow());
    }
}
