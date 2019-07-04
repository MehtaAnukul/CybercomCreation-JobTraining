package com.anukul.retrofitrecycleviewdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MoviesApi {
    //https://simplifiedcoding.net/demos/marvel/
    //get request
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<MovieModel>> getMovies();


}
