package com.anukul.mellofood;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

public class MallStallActivity extends AppCompatActivity implements MallStallOnItemClickListener{
    private Toolbar toolbar;
    private RecyclerView mallStallCustomRecyclerView;
    private ArrayList<MallStallModel> mallStallModelArrayList;
    private MallStallAdapter mallStallAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_stall);

        MallStallModel mallStallModel = new MallStallModel();
        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mallStallModel.getMallStallName());
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mallStallCustomRecyclerView = findViewById(R.id.activity_mallStall_recyclerView);

        mallStallModelArrayList = new ArrayList<>();
        mallStallModelArrayList.add(new MallStallModel("Domino's Pizza",R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("Sizzling",R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("La Pino'z Pizza",R.drawable.lapinozpizza));
        mallStallModelArrayList.add(new MallStallModel("Domino's Pizza",R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("Sizzling",R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("La Pino'z Pizza",R.drawable.lapinozpizza));
        mallStallModelArrayList.add(new MallStallModel("Domino's Pizza",R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("Sizzling",R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("La Pino'z Pizza",R.drawable.lapinozpizza));

        mallStallAdapter = new MallStallAdapter(mallStallModelArrayList,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mallStallCustomRecyclerView.setLayoutManager(layoutManager);
        mallStallCustomRecyclerView.setAdapter(mallStallAdapter);
    }

    @Override
    public void mallStallOnItemClick(MallStallModel mallStallModel) {
        Toast.makeText(this, ""+mallStallModel.getMallStallName(), Toast.LENGTH_SHORT).show();
    }
}
