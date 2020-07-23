package com.anukul.logindemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://www.splashbase.co/api/v1/images/";

    @GET("latest")
    Call<List<LoginModel>> getImage();
}
