package com.micro.shop.payment.access;

import java.io.Serializable;

public class PaymentOrder implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    private int orderId;
    private float price;

    public PaymentOrder() {
    }

    public PaymentOrder(int orderId, float price) {
        this.orderId = orderId;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PaymentOrder{" +
                "orderId=" + orderId +
                ", price=" + price +
                '}';
    }
}
