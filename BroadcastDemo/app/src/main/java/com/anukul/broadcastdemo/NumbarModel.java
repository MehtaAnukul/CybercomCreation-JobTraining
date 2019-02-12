package com.anukul.broadcastdemo;

public class NumbarModel {

    private int id;
    private String number;

    public NumbarModel(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public NumbarModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
