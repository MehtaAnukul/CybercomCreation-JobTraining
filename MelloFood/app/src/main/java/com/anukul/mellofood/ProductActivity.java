package com.anukul.mellofood;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductOnItemClickListener{

    private Toolbar toolbar;
    private RecyclerView productCustomRecyclerView;
    private ArrayList<ProductModel> productModelArrayList;
    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Product");
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setHomeAsUpIndicator(R.color.colorWhite);

        productCustomRecyclerView = findViewById(R.id.activity_product_recyclerView);

        productModelArrayList = new ArrayList<>();
        productModelArrayList.add(new ProductModel("Peppy Paneer","Peppy Paneer it's to good and with more cheesee and with more cheesee","$60.00",R.drawable.dominospizza));
        productModelArrayList.add(new ProductModel("Margherita","Margherita it's to cheese and with more cheesee and with more cheesee","$50.00",R.drawable.lapinozpizza));
        productModelArrayList.add(new ProductModel("Peppy Paneer","Peppy Paneer it's to good and with more cheesee and with more cheesee","$60.00",R.drawable.dominospizza));
        productModelArrayList.add(new ProductModel("Margherita","Margherita it's to cheese and with more cheesee and with more cheesee","$50.00",R.drawable.lapinozpizza));
        productModelArrayList.add(new ProductModel("Peppy Paneer","Peppy Paneer it's to good and with more cheesee and with more cheesee","$60.00",R.drawable.dominospizza));
        productModelArrayList.add(new ProductModel("Margherita","Margherita it's to cheese and with more cheesee and with more cheesee","$50.00",R.drawable.lapinozpizza));
        productModelArrayList.add(new ProductModel("Peppy Paneer","Peppy Paneer it's to good","$60.00",R.drawable.dominospizza));
        productModelArrayList.add(new ProductModel("Margherita","Margherita it's to cheese","$50.00",R.drawable.lapinozpizza));

        productAdapter = new ProductAdapter(productModelArrayList,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        productCustomRecyclerView.setLayoutManager(layoutManager);
        productCustomRecyclerView.setAdapter(productAdapter);
    }

    @Override
    public void productOnItemClick(ProductModel productModel) {
        Toast.makeText(this, ""+productModel.getProductName(), Toast.LENGTH_SHORT).show();
    }
}
