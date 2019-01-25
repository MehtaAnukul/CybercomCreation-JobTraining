package com.anukul.searchactionviewdemo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private ArrayList<String> statesOfIndiaArrayList;
    private IndiaStatesAdapter indiaStatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        recyclerView = findViewById(R.id.activity_main_recyclerView);

        statesOfIndiaArrayList = new ArrayList<>();
        statesOfIndiaArrayList.add("Jammu & kashmir");
        statesOfIndiaArrayList.add("Himanchal Pradesh");
        statesOfIndiaArrayList.add("Uttarachal");
        statesOfIndiaArrayList.add("Panjab");
        statesOfIndiaArrayList.add("Haryana");
        statesOfIndiaArrayList.add("Delhi");
        statesOfIndiaArrayList.add("Rajesthan");
        statesOfIndiaArrayList.add("Gujrat");
        statesOfIndiaArrayList.add("Maharastra");
        statesOfIndiaArrayList.add("Goa");
        statesOfIndiaArrayList.add("Karnataka");
        statesOfIndiaArrayList.add("Kerala");
        statesOfIndiaArrayList.add("Tamilnadu");
        statesOfIndiaArrayList.add("Pondicherry");
        statesOfIndiaArrayList.add("Uttar Pradesh");
        statesOfIndiaArrayList.add("Bihar");
        statesOfIndiaArrayList.add("Jharkhand");
        statesOfIndiaArrayList.add("Chhattisgath");
        statesOfIndiaArrayList.add("Orrisa");

        indiaStatesAdapter = new IndiaStatesAdapter(statesOfIndiaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(indiaStatesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, "Action View Expanded", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, "Action View Collapsed", Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        MenuItem menuItem = menu.findItem(R.id.toolbat_menu_searchIcon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String userText = s.toLowerCase();

        ArrayList<String> searchArrayList = new ArrayList<>();

        for (int i = 0; i < statesOfIndiaArrayList.size(); i++) {
            if (statesOfIndiaArrayList.get(i).toLowerCase().contains(userText)){
                searchArrayList.add(statesOfIndiaArrayList.get(i));
            }

        }

        indiaStatesAdapter.updateList(searchArrayList);
        return true;
    }
}
