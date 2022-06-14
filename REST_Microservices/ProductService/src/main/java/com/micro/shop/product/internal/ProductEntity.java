package com.micro.shop.product.internal;

import com.micro.shop.product.access.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @Column(name = "nr")
    private Integer nr;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    public ProductEntity() {
    }

    public ProductEntity(Product product) {
        this.nr = product.getNr();
        this.name = product.getName();
        this.price = product.getPrice();
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

    @Override
    public String toString() {
        return "ProductEntity{" +
                "nr=" + nr +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(nr, that.nr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr);
    }
}
