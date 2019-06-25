package com.anukul.retrofitjsondemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    //https://simplifiedcoding.net/demos/marvel/
    //get request
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<JsonDataModel>> getJsonDatas();

}
