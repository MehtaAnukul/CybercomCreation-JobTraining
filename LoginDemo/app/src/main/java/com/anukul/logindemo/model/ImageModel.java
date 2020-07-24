package com.anukul.logindemo.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageModel {
    @SerializedName("images")
    @Expose
    private List<ImageDataModel> images = null;

    public List<ImageDataModel> getImages() {
        return images;
    }

    public void setImages(List<ImageDataModel> images) {
        this.images = images;
    }

}
