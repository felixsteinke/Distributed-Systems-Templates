package com.micro.shop.abo.access;

import com.micro.shop.abo.internal.AboEntity;

public class Abo {

    private Integer id;
    private Integer productNr;
    private String productName;
    private Float price;
    private Boolean payed;

    public Abo() {
    }

    public Abo(AboEntity aboEntity) {
        this.id = aboEntity.getId();
        this.productNr = aboEntity.getProductNr();
        this.productName = aboEntity.getProductName();
        this.price = aboEntity.getPrice();
        this.payed = aboEntity.getPayed();
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
}
