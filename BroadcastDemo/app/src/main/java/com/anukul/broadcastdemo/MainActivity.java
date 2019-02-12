package com.anukul.broadcastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anukul.broadcastdemo.app.ContactDbConstant;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView numberCustomRecyclerView;
    private ArrayList<NumbarModel> numbarModelArrayList;
    private ReadNumberAdapter readNumberAdapter;
    private TextView noIncomigTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noIncomigTv = findViewById(R.id.activity_main_noIncomingTv);
        numberCustomRecyclerView = findViewById(R.id.activity_main_recyclerView);

        numbarModelArrayList = new ArrayList<>();
        ContactDbHelper contactDbHelper = new ContactDbHelper(this);
        numbarModelArrayList = contactDbHelper.getAllNumber();
        contactDbHelper.close();
        readNumberAdapter.notifyDataSetChanged();
        numberCustomRecyclerView.setVisibility(View.VISIBLE);
        noIncomigTv.setVisibility(View.GONE);

        readNumberAdapter = new ReadNumberAdapter(numbarModelArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        numberCustomRecyclerView.setLayoutManager(layoutManager);
        numberCustomRecyclerView.setAdapter(readNumberAdapter);
        Toast.makeText(this, "read Success", Toast.LENGTH_SHORT).show();
    }
}
