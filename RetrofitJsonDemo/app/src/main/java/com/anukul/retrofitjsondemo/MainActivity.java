package com.anukul.retrofitjsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.activity_main_jsonDataName_ListView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<JsonDataModel>> call = api.getJsonDatas();

        call.enqueue(new Callback<List<JsonDataModel>>() {
            @Override
            public void onResponse(Call<List<JsonDataModel>> call, Response<List<JsonDataModel>> response) {
                List<JsonDataModel> jsonDataModelList = response.body();

                String jsonDataNames[] = new String[jsonDataModelList.size()];

                for (int i = 0; i<jsonDataModelList.size(); i++){

                    jsonDataNames[i] = jsonDataModelList.get(i).getName();
                }
                listView.setAdapter(new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        jsonDataNames)
                );

              /*  for(JsonDataModel i : jsonDataModelList){
                    Log.d("MainActivityName",i.getName());
                    Log.d("MainActivityName",i.getRealname());
                    Log.d("MainActivityName",i.getCreatedby());
                }*/
            }

            @Override
            public void onFailure(Call<List<JsonDataModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}
