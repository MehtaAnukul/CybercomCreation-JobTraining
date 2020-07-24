package com.anukul.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.anukul.logindemo.model.ImageModel;
import com.anukul.logindemo.network.ApiClient;

import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    private GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gridView = findViewById(R.id.activity_grid_gridView);

        //make network call
        Call<List<ImageModel>> call = ApiClient.apiInterface().getImageModel();

        call.enqueue(new Callback<List<ImageModel>>() {
            @Override
            public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                if(response.isSuccessful()){
                    gridAdapter = new GridAdapter(response.body(),GridActivity.this);

                    gridView.setAdapter(gridAdapter);
                }else {
                    Toast.makeText(GridActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                Toast.makeText(GridActivity.this, "Something want wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}