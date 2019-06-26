package com.anukul.retrofitrecycleviewdemo;

import com.google.gson.annotations.SerializedName;

public class MovieModel {
//jsonData and your model class variable same name bcz
    //it's case sencetive
    //if you assign diffrent variable name in model class
    // so you put @SerializedName("your json variable name")

    private String id;
    private int image;
    private String is_new;
    private String rating;
    private String like_percent;
    private String vote_count;
    private String title;
    private String language;
    private String type;

    public MovieModel(String id, int image, String is_new, String rating, String like_percent, String vote_count, String title, String language, String type) {
        this.id = id;
        this.image = image;
        this.is_new = is_new;
        this.rating = rating;
        this.like_percent = like_percent;
        this.vote_count = vote_count;
        this.title = title;
        this.language = language;
        this.type = type;
    }

    public MovieModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLike_percent() {
        return like_percent;
    }

    public void setLike_percent(String like_percent) {
        this.like_percent = like_percent;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    /*val id: Int,
    val image: String,
    @SerializedName("is_new")
    val isNew: Int,
    val rating: String,
    @SerializedName("like_percent")
    val likePercent: Int,
    @SerializedName("vote_count")
    val voteCount: Int,
    val title:String,
    val language:String,
    val type: String*/
}
