package com.anukul.logindemo.network;

import com.anukul.logindemo.model.GridModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

   @GET("latest")
   Call<List<GridModel>> getGridModel();
}
