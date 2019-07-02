package com.anukul.retrofitjsondemo;



public class JsonDataModel {
    //jsonData and your model class variable same name bcz
    //it's case sencetive
    //if you assign diffrent variable name in model class
    // so you put @SerializedName("your json variable name")

    private String name;
    private String realname;
    private String team;
    private String firstappearance;
    private String createdby;
    private String publisher;
    private String imgeurl;
    private String bio;

    public JsonDataModel(String name, String realname, String team, String firstappearance, String createdby, String publisher, String imgeurl, String bio) {
        this.name = name;
        this.realname = realname;
        this.team = team;
        this.firstappearance = firstappearance;
        this.createdby = createdby;
        this.publisher = publisher;
        this.imgeurl = imgeurl;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFirstappearance() {
        return firstappearance;
    }

    public void setFirstappearance(String firstappearance) {
        this.firstappearance = firstappearance;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImgeurl() {
        return imgeurl;
    }

    public void setImgeurl(String imgeurl) {
        this.imgeurl = imgeurl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

//json data
    /*"name":"Captain America",
            "realname":"Steve Rogers",
            "team":"Avengers",
            "firstappearance":"1941",
            "createdby":"Joe Simon",
            "publisher":"Marvel Comics",
            "imageurl":"https:\/\/www.simplifiedcoding.net\/demos\/marvel\/captainamerica.jpg",
            "bio":"\r*/
