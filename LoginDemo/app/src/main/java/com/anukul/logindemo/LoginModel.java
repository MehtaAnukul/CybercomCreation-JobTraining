package com.anukul.logindemo;

import java.util.List;

public class LoginModel {


    private int id;
    private String url;
    private String large_url;
    private String source_id;

    public LoginModel(int id, String url, String large_url, String source_id) {
        this.id = id;
        this.url = url;
        this.large_url = large_url;
        this.source_id = source_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLarge_url() {
        return large_url;
    }

    public void setLarge_url(String large_url) {
        this.large_url = large_url;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }
}
