package com.anukul.mellofood;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class OutletActivity extends AppCompatActivity implements OutletOnItemClickListener , NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private RecyclerView outletCustomRecyclerView;
    private ArrayList<OutletModel> outletModelArrayList;
    private OutletAdapter outletAdapter;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("OUTLETS");
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.activty_outlet_drawerLayout);
        navigationView = findViewById(R.id.activity_outlet_navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        outletCustomRecyclerView = findViewById(R.id.activity_outlet_recyclerView);

        outletModelArrayList = new ArrayList<>();
        outletModelArrayList.add(new OutletModel("Himalaya Mall",R.drawable.himalayamall));
        outletModelArrayList.add(new OutletModel("Alpha One Mall",R.drawable.himalayamall));
        outletModelArrayList.add(new OutletModel("Central Mall",R.drawable.himalayamall));
        outletModelArrayList.add(new OutletModel("Iscon Mall",R.drawable.himalayamall));
        outletModelArrayList.add(new OutletModel("Honest",R.drawable.himalayamall));
        outletModelArrayList.add(new OutletModel("Reliance Mall",R.drawable.himalayamall));

        outletAdapter = new OutletAdapter(outletModelArrayList,this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false);
        outletCustomRecyclerView.setLayoutManager(layoutManager);
        outletCustomRecyclerView.setAdapter(outletAdapter);
    }

    @Override
    public void outletOnItemClick(OutletModel outletModel) {
        Toast.makeText(this, ""+outletModel.getMallName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.MyAccount:
                menuItem.setChecked(true);
                displayMessage("My Account Selected");
                drawerLayout.closeDrawers();
                break;
            case R.id.outlets:
                menuItem.setChecked(true);
                displayMessage("Outlets Selected");
                drawerLayout.closeDrawers();
                break;

            case R.id.order:
                menuItem.setChecked(true);
                displayMessage("Order Selected");
                drawerLayout.closeDrawers();
                break;

            case R.id.payment:
                menuItem.setChecked(true);
                displayMessage("Payment Method Selected");
                drawerLayout.closeDrawers();
                break;

            case R.id.promotion:
                menuItem.setChecked(true);
                displayMessage("Promotions Selected");
                drawerLayout.closeDrawers();
                break;


            case R.id.help:
                menuItem.setChecked(true);
                displayMessage("Help Selected");
                drawerLayout.closeDrawers();
                break;


            case R.id.contactus:
                menuItem.setChecked(true);
                displayMessage("Contact US Selected");
                drawerLayout.closeDrawers();
                break;


            case R.id.privacyPolicy:
                menuItem.setChecked(true);
                displayMessage("Privacy Policy Selected");
                drawerLayout.closeDrawers();
                break;


            case R.id.terms:
                menuItem.setChecked(true);
                displayMessage("Terms And Conditions Selected");
                drawerLayout.closeDrawers();
                break;


            case R.id.aboutus:
                menuItem.setChecked(true);
                displayMessage("About US Selected");
                drawerLayout.closeDrawers();
                break;

            case R.id.logout:
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return false;
    }

    private void displayMessage(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
