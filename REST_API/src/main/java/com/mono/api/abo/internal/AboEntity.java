package com.mono.api.abo.internal;

import com.mono.api.product.access.Product;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "abos")
public class AboEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "p_nr")
    private Integer productNr;

    @Column(name = "p_name")
    private String productName;

    @Column(name = "price")
    private Float price;

    @Column(name = "payed")
    private Boolean payed;

    public AboEntity() {
    }

    public AboEntity(Product product) {
        this.productNr = product.getNr();
        this.productName = product.getName();
        this.price = product.getPrice();
        this.payed = false;
    }

    public Integer getId() {
        return id;
    }

    public Integer getProductNr() {
        return productNr;
    }

    public String getProductName() {
        return productName;
    }

    public Float getPrice() {
        return price;
    }

    public Boolean getPayed() {
        return payed;
    }

    public AboEntity setPayed(Boolean payed) {
        this.payed = payed;
        return this;
    }

    @Override
    public String toString() {
        return "AboEntity{" +
                "id=" + id +
                ", productNr=" + productNr +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", payed=" + payed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AboEntity aboEntity = (AboEntity) o;
        return Objects.equals(id, aboEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
