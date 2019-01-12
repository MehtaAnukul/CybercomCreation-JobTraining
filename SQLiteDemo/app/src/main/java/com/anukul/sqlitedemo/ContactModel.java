package com.anukul.sqlitedemo;

public class ContactModel {
    private int id;
    private int name;
    private int email;

    public ContactModel(int id, int name, int email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public ContactModel(int name, int email) {
        this.name = name;
        this.email = email;
    }

    public ContactModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }
}
