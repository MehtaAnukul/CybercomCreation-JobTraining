package com.anukul.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.anukul.retrofitexample.model.Movie;
import com.anukul.retrofitexample.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "c17fbafb7a92b964d7a058120682e0b4";
    private List<Movie> movieList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.movie_rv);
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movieList = response.body().getResults();

                Log.e("MOVIE", "Number of movies received: " + movieList.size());
                recyclerView.setAdapter(new MoviesAdapter(movieList, R.layout.list_item_movie, MainActivity.this));
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                for (Movie movie : movieList) {
                    Log.e("MOVIE", "movies : " + movie.getTitle());

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("MOV", t.toString());
            }
        });
    }
}
