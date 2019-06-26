package com.anukul.retrofitrecycleviewdemo;






import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class MainActivity extends AppCompatActivity implements ItemClickListener{
    private RecyclerView moviesCustomRecyclerView;
    private ArrayList<MovieModel> movieModelArrayList;
    private MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesCustomRecyclerView = findViewById(R.id.activity_main_recyclerView);

        movieModelArrayList = new ArrayList<>();
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

        movieAdapter = new MovieAdapter(movieModelArrayList,this);

        RecyclerView.LayoutManager customlayoutManager = new LinearLayoutManager(this);
        moviesCustomRecyclerView.setLayoutManager(customlayoutManager);
        moviesCustomRecyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void onItemClick(MovieModel movieModel, View v) {
        Toast.makeText(this, ""+movieModel.getLanguage(), Toast.LENGTH_SHORT).show();
    }
}
