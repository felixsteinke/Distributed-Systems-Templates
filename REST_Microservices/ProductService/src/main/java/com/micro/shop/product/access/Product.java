package com.micro.shop.product.access;


import com.micro.shop.product.internal.ProductEntity;

public class Product {

    private Integer nr;
    private String name;
    private Float price;

    public Product() {
    }

    public Product(ProductEntity productEntity) {
        this.nr = productEntity.getNr();
        this.name = productEntity.getName();
        this.price = productEntity.getPrice();
    }

    public Integer getNr() {
        return nr;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }
}
