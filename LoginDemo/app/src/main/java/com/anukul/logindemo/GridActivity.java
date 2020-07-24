package com.anukul.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.anukul.logindemo.model.GridModel;
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
        Call<List<GridModel>> call = ApiClient.apiInterface().getGridModel();

        call.enqueue(new Callback<List<GridModel>>() {
            @Override
            public void onResponse(Call<List<GridModel>> call, Response<List<GridModel>> response) {
                if(response.isSuccessful()){
                    gridAdapter = new GridAdapter(response.body(),GridActivity.this);

                    gridView.setAdapter(gridAdapter);
                }else {
                    Toast.makeText(GridActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GridModel>> call, Throwable t) {
                Toast.makeText(GridActivity.this, "Something want wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}