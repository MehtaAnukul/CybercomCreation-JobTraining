package com.anukul.paginglibrarydemo;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer,ItemModel> {
   public static final int PAGE_SIZE = 50;
    private static final int FIRST_PAGE = 1;
    private static final String SITE_NAME = "stackoverflow";


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, ItemModel> callback) {
       RetrofitClient.getInstance()
                    .getApi()
                    .getAnswers(FIRST_PAGE,PAGE_SIZE,SITE_NAME)
                    .enqueue(new Callback<StackApiResponseModel>() {
                        @Override
                        public void onResponse(Call<StackApiResponseModel> call, Response<StackApiResponseModel> response) {

                            if(response.body() != null){
                                callback.onResult(response.body().items,null,FIRST_PAGE + 1);

                            }
                        }

                        @Override
                        public void onFailure(Call<StackApiResponseModel> call, Throwable t) {

                        }
                    });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ItemModel> callback) {

        RetrofitClient.getInstance()
                    .getApi()
                    .getAnswers(params.key,PAGE_SIZE,SITE_NAME)
                    .enqueue(new Callback<StackApiResponseModel>() {
                        @Override
                        public void onResponse(Call<StackApiResponseModel> call, Response<StackApiResponseModel> response) {

                            if(response.body() != null){
                                Integer key = (params.key > 1) ? params.key - 1 : null;
                                callback.onResult(response.body().items,key);
                            }
                        }
                        @Override
                        public void onFailure(Call<StackApiResponseModel> call, Throwable t) {

                        }
                    });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ItemModel> callback) {

            RetrofitClient.getInstance()
                        .getApi()
                        .getAnswers(params.key,PAGE_SIZE,SITE_NAME)
                        .enqueue(new Callback<StackApiResponseModel>() {
                            @Override
                            public void onResponse(Call<StackApiResponseModel> call, Response<StackApiResponseModel> response) {

                                if(response.body() != null){
                                    Integer key = response.body().has_more ? params.key + 1 : null;
                                    callback.onResult(response.body().items,key);
                                }
                            }
                            @Override
                            public void onFailure(Call<StackApiResponseModel> call, Throwable t) {

                            }
                        });
    }
}
