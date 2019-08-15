package com.anukul.paginglibrarydemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("answers")
    Call<StackApiResponseModel> getAnswers(
            @Query("page") int page,
            @Query("pagesize") int size,
            @Query("site") String site
    );
}
