package com.anukul.mellofood;

public class ProductModel {
    private String productName;
    private String productDescription;
    private String productPrice;
    private int productImgRes;

    public ProductModel(String productName, String productDescription, String productPrice, int productImgRes) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImgRes = productImgRes;
    }

    public ProductModel() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductImgRes() {
        return productImgRes;
    }

    public void setProductImgRes(int productImgRes) {
        this.productImgRes = productImgRes;
    }
}
