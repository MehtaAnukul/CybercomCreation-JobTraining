package com.anukul.mellofood.model;

public class OrderModel {
    private String orderProductName;
    private String orderProductDescription;
    private String orderProductItem;
    private String orderProductPrice;

    public OrderModel(String orderProductName, String orderProductDescription, String orderProductItem, String orderProductPrice) {
        this.orderProductName = orderProductName;
        this.orderProductDescription = orderProductDescription;
        this.orderProductItem = orderProductItem;
        this.orderProductPrice = orderProductPrice;
    }

    public OrderModel() {
    }

    public String getOrderProductName() {
        return orderProductName;
    }

    public void setOrderProductName(String orderProductName) {
        this.orderProductName = orderProductName;
    }

    public String getOrderProductDescription() {
        return orderProductDescription;
    }

    public void setOrderProductDescription(String orderProductDescription) {
        this.orderProductDescription = orderProductDescription;
    }

    public String getOrderProductItem() {
        return orderProductItem;
    }

    public void setOrderProductItem(String orderProductItem) {
        this.orderProductItem = orderProductItem;
    }

    public String getOrderProductPrice() {
        return orderProductPrice;
    }

    public void setOrderProductPrice(String orderProductPrice) {
        this.orderProductPrice = orderProductPrice;
    }
}
