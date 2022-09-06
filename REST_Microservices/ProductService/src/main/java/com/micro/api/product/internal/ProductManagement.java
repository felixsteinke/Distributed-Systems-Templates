package com.micro.api.product.internal;

import com.micro.api.product.IProductService;
import com.micro.api.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductManagement implements IProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductManagement(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product) {
        ProductEntity input = ProductMapper.entity(product);
        ProductEntity newEntity = productRepo.save(input);
        return ProductMapper.model(newEntity);
    }

    public void deleteProduct(Integer productNr) {
        productRepo.deleteById(productNr);
    }

    public List<Product> getAllProducts() {
        List<ProductEntity> table = productRepo.findAll();
        return table.stream().map(ProductMapper::model).collect(Collectors.toList());
    }

    @Override
    public Product getProduct(Integer productNr) {
        ProductEntity entity = productRepo.findById(productNr).orElseThrow();
        return ProductMapper.model(entity);
    }
}
