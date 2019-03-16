package com.anukul.mellofood;

public class MallStallModel {
    private String mallStallName;
    private int mallStallImgRes;

    public MallStallModel(String mallStallName, int mallStallImgRes) {
        this.mallStallName = mallStallName;
        this.mallStallImgRes = mallStallImgRes;
    }

    public MallStallModel() {
    }

    public String getMallStallName() {
        return mallStallName;
    }

    public void setMallStallName(String mallStallName) {
        this.mallStallName = mallStallName;
    }

    public int getMallStallImgRes() {
        return mallStallImgRes;
    }

    public void setMallStallImgRes(int mallStallImgRes) {
        this.mallStallImgRes = mallStallImgRes;
    }
}
