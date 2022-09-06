package com.mono.api.abo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // lombok default constructor
@Getter // lombok generates getter for all fields
@Setter // lombok generates setter for all fields
public class Abo {

    private Integer id;
    private Integer productNr;
    private String productName;
    private Float price;
    private Boolean payed;

}
