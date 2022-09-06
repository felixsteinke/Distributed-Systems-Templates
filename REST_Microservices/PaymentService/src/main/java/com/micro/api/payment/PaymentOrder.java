package com.micro.api.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class PaymentOrder implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    private int orderId;
    private float price;

    public PaymentOrder(int orderId, float price) {
        this.orderId = orderId;
        this.price = price;
    }

    @Override
    public String toString() {
        return "PaymentOrder{" +
                "orderId=" + orderId +
                ", price=" + price +
                '}';
    }
}
