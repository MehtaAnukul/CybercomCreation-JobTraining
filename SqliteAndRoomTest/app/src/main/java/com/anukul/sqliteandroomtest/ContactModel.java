package com.anukul.sqliteandroomtest;

class ContactModel {
    private int id;
    private String fName;
    private String lName;
    private String phoneNo;
    private String email;

    public ContactModel(int id, String fName, String lName, String phoneNo, String email) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public ContactModel() {

    }

    public ContactModel(String fName, String lName, String phoneNo, String email) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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
}
