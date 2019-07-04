package com.anukul.retrofitrecycleviewdemo;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements ItemClickListener {
    private RecyclerView moviesCustomRecyclerView;
    private List<MovieModel> movieModelList;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesCustomRecyclerView = findViewById(R.id.activity_main_recyclerView);

      /*  movieModelArrayList = new ArrayList<>();
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","English",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","Hindi",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","English",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","English",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","English",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","English",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","English",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","English",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","Japaneess",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","English",""));
        movieModelArrayList.add(new MovieModel("1",R.drawable.captain_marvel,"true","UA","73","2300","Captain Marvel","English",""));
*/
        //movieAdapter = new MovieAdapter(movieModelArrayList,this,this);

        RecyclerView.LayoutManager customlayoutManager = new LinearLayoutManager(this);


        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MoviesApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviesApi moviesApi = retrofit.create(MoviesApi.class);

        Call<List<MovieModel>> movieModelCall = moviesApi.getMovies();

        movieModelCall.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                // MovieModel movieModel = (MovieModel) response.body();

                movieModelList = response.body();
                moviesCustomRecyclerView.setAdapter(new MovieAdapter(movieModelList, R.layout.custom_layout_movie, MainActivity.this));
                moviesCustomRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                //moviesCustomRecyclerView.setLayoutManager(customlayoutManager);

                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                Log.e("NEWNEW", t.getMessage() + t.getCause());
                Toast.makeText(MainActivity.this, "Error Occured" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(MovieModel movieModel, View v) {

        /*Toast.makeText(this, ""+movieModel.getLanguage(), Toast.LENGTH_SHORT).show();

        switch (v.getId()){
            case R.id.custom_layout_textViewIsNew:
                Toast.makeText(this, ""+movieModel.getIs_new(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_layout_textViewLanguage:
                Toast.makeText(this, ""+movieModel.getLanguage(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_layout_textViewLikePercent:
                Toast.makeText(this, ""+movieModel.getLike_percent(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_layout_textViewRating:
                Toast.makeText(this, ""+movieModel.getRating(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_layout_textViewTitle:
                Toast.makeText(this, ""+movieModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;*/
        // }
    }
}
