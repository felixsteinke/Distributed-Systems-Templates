package com.micro.api.external;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // lombok default constructor
@Getter // lombok generates getter for all fields
@Setter // lombok generates setter for all fields
public class Product {

    private Integer nr;
    private String name;
    private Float price;

}
