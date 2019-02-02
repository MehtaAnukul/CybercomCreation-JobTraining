package com.anukul.contextmenuwithrecyclerview;

public class FruitModel {
    private String fruitName;
    private int fruitImgRes;

    public FruitModel(String fruitName, int fruitImgRes) {
        this.fruitName = fruitName;
        this.fruitImgRes = fruitImgRes;
    }

    public FruitModel() {

    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getFruitImgRes() {
        return fruitImgRes;
    }

    public void setFruitImgRes(int fruitImgRes) {
        this.fruitImgRes = fruitImgRes;
    }
}
