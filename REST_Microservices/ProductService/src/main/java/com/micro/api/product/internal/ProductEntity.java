package com.micro.api.product.internal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Database Access Object (DAO).
 * <p>Package private and managed by the {@link ProductManagement} and {@link ProductMapper}.</p>
 */
@Entity // jpa entity configuration
@Table(name = "products") // jpa table configuration
@NoArgsConstructor // lombok default constructor - required for jpa entities
@Getter // lombok generates getter for all fields
@Setter // lombok generates setter for all fields
@ToString // lombok generates toString including all fields
class ProductEntity {

    @Id
    @Column(name = "nr")
    private Integer nr;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

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
