package com.micro.shop.product;

import com.micro.shop.product.access.Product;
import com.micro.shop.product.internal.ProductManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ProductController {

    private final ProductManagement productManagement;

    @Autowired
    public ProductController(ProductManagement productManagement) {
        this.productManagement = productManagement;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productManagement.getAllProducts());
    }

    @GetMapping(path = "/get/{productNr}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable Integer productNr) {
        return ResponseEntity.ok(productManagement.getProduct(productNr));
    }

    @PostMapping(path = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productManagement.addProduct(product));
    }

    @DeleteMapping(path = "/delete/{productNr}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productNr) {
        productManagement.deleteProduct(productNr);
        return ResponseEntity.ok().build();
    }
}
