package com.anukul.sqlitelogindemo;

public class ContactModel {
    private int id;
    private String name;
    private String lastName;
    private String phoneNo;
    private String email;
    private String password;
    private String gender;

    public ContactModel(int id, String name, String lastName, String phoneNo, String email, String password, String gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public ContactModel() {
    }

    public ContactModel(String name, String lastName, String phoneNo, String email, String password, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public ContactModel(int id, String name, String lastName, String phoneNo, String email, String gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

