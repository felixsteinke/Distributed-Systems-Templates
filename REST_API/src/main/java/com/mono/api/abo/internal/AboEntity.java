package com.mono.api.abo.internal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Database Access Object (DAO).
 * <p>Package private and managed by the {@link AboManagement} and {@link AboMapper}.</p>
 */
@Entity // jpa entity configuration
@Table(name = "abos") // jpa table configuration
@NoArgsConstructor // lombok default constructor
@Getter // lombok generates getter for all fields
@ToString // lombok generates toString including all fields
class AboEntity {

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

    public AboEntity setProductNr(Integer productNr) {
        this.productNr = productNr;
        return this;
    }

    public AboEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public AboEntity setPrice(Float price) {
        this.price = price;
        return this;
    }

    public AboEntity setPayed(Boolean payed) {
        this.payed = payed;
        return this;
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
