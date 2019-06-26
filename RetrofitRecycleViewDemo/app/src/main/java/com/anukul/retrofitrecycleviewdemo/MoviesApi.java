package com.anukul.retrofitrecycleviewdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MoviesApi {
    //https://simplifiedcoding.net/demos/marvel/
    //get request
    String BASE_URL = "https://www.simplifiedcoding.in/course-api/";

    @GET("movies")
    Call<List<MovieModel>> getMovies();

    /*Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();*/
}
