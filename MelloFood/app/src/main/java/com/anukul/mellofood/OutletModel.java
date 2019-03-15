package com.anukul.mellofood;

public class OutletModel {
    private String mallName;
    private int mallImage;

    public OutletModel(String mallName, int mallImage) {
        this.mallName = mallName;
        this.mallImage = mallImage;
    }

    public OutletModel() {
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public int getMallImage() {
        return mallImage;
    }

    public void setMallImage(int mallImage) {
        this.mallImage = mallImage;
    }
}
