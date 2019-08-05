package com.anukul.retrofitlogindemo;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;


public interface UserService {

    @GET("78lql")
    Call<LoginModel> login(
            @Field("username") String username,
            @Field("password") String password
    );

}
