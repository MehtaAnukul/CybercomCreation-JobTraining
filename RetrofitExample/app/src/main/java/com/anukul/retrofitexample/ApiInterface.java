package com.anukul.retrofitexample;

import com.anukul.retrofitexample.model.MovieResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //http://api.themoviedb.org/3/movie/top_rated?api_key=c17fbafb7a92b964d7a058120682e0b4
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
   // @GET("marvel")
   // Call<List<MovieModel>> getMovies();