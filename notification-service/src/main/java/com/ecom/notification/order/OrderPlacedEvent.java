package com.ecom.notification.order;

public class OrderPlacedEvent {

    private String orderNumber;
    private String email;

    public OrderPlacedEvent(String orderNumber, String email) {
        this.orderNumber = orderNumber;
        this.email = email;
    }

    public OrderPlacedEvent() {
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
