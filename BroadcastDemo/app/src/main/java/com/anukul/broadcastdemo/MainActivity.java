package com.anukul.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anukul.broadcastdemo.app.ContactDbConstant;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView numberCustomRecyclerView;
    private List<NumbarModel> numbarModelArrayList;
    private ReadNumberAdapter readNumberAdapter;
    private TextView noIncomigTv;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noIncomigTv = findViewById(R.id.activity_main_noIncomingTv);
        numberCustomRecyclerView = findViewById(R.id.activity_main_recyclerView);
        numbarModelArrayList = new ArrayList<>();
        readNumberAdapter = new ReadNumberAdapter(numbarModelArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        numberCustomRecyclerView.setLayoutManager(layoutManager);
        numberCustomRecyclerView.setAdapter(readNumberAdapter);


        readContact();

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                readContact();
            }
        };
    }

    private void readContact() {
        numbarModelArrayList.clear();
        ContactDbHelper contactDbHelper = new ContactDbHelper(MainActivity.this);
        SQLiteDatabase db = contactDbHelper.getReadableDatabase();
        Log.e("ContentValue", "app running");
        Cursor cursor = contactDbHelper.getAllNumber(db);

        if (cursor.getCount() > 0) {
            Log.e("ContentValue", "read data conform");
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_ID));
                String number = cursor.getString(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_INCOMING_NO));
                numbarModelArrayList.add(new NumbarModel(id, number));
            }
            cursor.close();
            contactDbHelper.close();
            readNumberAdapter.notifyDataSetChanged();
            numberCustomRecyclerView.setVisibility(View.VISIBLE);
            noIncomigTv.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "read Success", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver,new IntentFilter(ContactDbConstant.UPDATE_UI_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}
