package com.anukul.contextmenuwithrecyclerview;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    private RecyclerView recyclerView;
    private ArrayList<FruitModel> fruitModelArrayList;
    private FruitAdapter fruitAdapter;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.activity_main_coordinatorLayout);
        recyclerView = findViewById(R.id.activity_main_recyclerView);

        fruitModelArrayList = new ArrayList<>();
        fruitModelArrayList.add(new FruitModel("Apple",R.drawable.apple));
        fruitModelArrayList.add(new FruitModel("avocado",R.drawable.avocado));
        fruitModelArrayList.add(new FruitModel("Banana",R.drawable.banana));
        fruitModelArrayList.add(new FruitModel("Cherry",R.drawable.cherry));
        fruitModelArrayList.add(new FruitModel("Grape",R.drawable.grape));
        fruitModelArrayList.add(new FruitModel("Kiwi",R.drawable.kiwi));
        fruitModelArrayList.add(new FruitModel("Lemon",R.drawable.lemon));
        fruitModelArrayList.add(new FruitModel("Mengo",R.drawable.mango));
        fruitModelArrayList.add(new FruitModel("Orange",R.drawable.orange));
        fruitModelArrayList.add(new FruitModel("Strawberry",R.drawable.strawberry));
        fruitModelArrayList.add(new FruitModel("Watermelon",R.drawable.watermelon));

        fruitAdapter = new FruitAdapter(fruitModelArrayList,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fruitAdapter);

    }

    @Override
    public void onItemClick(FruitModel fruitModel) {
        Toast.makeText(this, ""+fruitModel.getFruitName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 11:
                fruitAdapter.removeItem(item.getGroupId());
                displayMessage("Item Deleted");
                break;
            case 12:
                displayMessage("Item add to WishList");
                break;

        }
        return super.onContextItemSelected(item);
    }

    private void displayMessage(String message) {
        Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_SHORT).show();
    }


}
