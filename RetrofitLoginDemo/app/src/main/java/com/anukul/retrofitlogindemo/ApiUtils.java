package com.anukul.retrofitlogindemo;

public class ApiUtils {
    public static final String BASE_URL = "https://api.myjson.com/bins/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}
