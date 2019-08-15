package com.anukul.paginglibrarydemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.activity_main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        final ItemAdapter adapter = new ItemAdapter(this);

        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<ItemModel>>() {
            @Override
            public void onChanged(@Nullable PagedList<ItemModel> itemModels) {
                adapter.submitList(itemModels);
            }
        });

        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "Success but some time ban the api few second", Toast.LENGTH_SHORT).show();
    }
}
