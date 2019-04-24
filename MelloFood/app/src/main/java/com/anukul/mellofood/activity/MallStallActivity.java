package com.anukul.mellofood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.anukul.mellofood.adapter.MallStallAdapter;
import com.anukul.mellofood.model.MallStallModel;
import com.anukul.mellofood.listener.MallStallOnItemClickListener;
import com.anukul.mellofood.R;

import java.util.ArrayList;

public class MallStallActivity extends AppCompatActivity implements MallStallOnItemClickListener {
    private Toolbar toolbar;
    private RecyclerView mallStallCustomRecyclerView;
    private ArrayList<MallStallModel> mallStallModelArrayList;
    private MallStallAdapter mallStallAdapter;
    private TextView toolbarTitleTv;
    private String mallName;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_stall);
        intent = getIntent();

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbarTitleTv = toolbar.findViewById(R.id.toolbar_title);
        mallName = intent.getStringExtra("KEY_TITLE");
        toolbarTitleTv.setText(mallName);



        mallStallCustomRecyclerView = findViewById(R.id.activity_mallStall_recyclerView);

        mallStallModelArrayList = new ArrayList<>();
        mallStallModelArrayList.add(new MallStallModel("Domino's Pizza", R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("Sizzling", R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("La Pino'z Pizza", R.drawable.lapinozpizza));
        mallStallModelArrayList.add(new MallStallModel("Domino's Pizza", R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("Sizzling", R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("La Pino'z Pizza", R.drawable.lapinozpizza));
        mallStallModelArrayList.add(new MallStallModel("Domino's Pizza", R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("Sizzling", R.drawable.dominospizza));
        mallStallModelArrayList.add(new MallStallModel("La Pino'z Pizza", R.drawable.lapinozpizza));

        mallStallAdapter = new MallStallAdapter(mallStallModelArrayList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mallStallCustomRecyclerView.setLayoutManager(layoutManager);
        mallStallCustomRecyclerView.setAdapter(mallStallAdapter);
        Toast.makeText(this, "AGAIN", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mallStallOnItemClick(MallStallModel mallStallModel) {
        Toast.makeText(this, "" + mallStallModel.getMallStallName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("KEY_TITLE", mallStallModel.getMallStallName());
        startActivity(intent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbarTitleTv.setText(mallName);
        Toast.makeText(this, "" + toolbarTitleTv.getText(), Toast.LENGTH_SHORT).show();
    }
}
