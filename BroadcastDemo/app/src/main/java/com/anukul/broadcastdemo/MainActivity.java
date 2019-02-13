package com.anukul.broadcastdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        readNumberAdapter = new ReadNumberAdapter(numbarModelArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        numberCustomRecyclerView.setLayoutManager(layoutManager);
        numberCustomRecyclerView.setAdapter(readNumberAdapter);

        ContactDbHelper contactDbHelper = new ContactDbHelper(this);
        SQLiteDatabase db = contactDbHelper.getReadableDatabase();
        Cursor cursor = contactDbHelper.getAllNumber(db);
        if (cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_ID));
                String number = cursor.getString(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_INCOMING_NO));
                numbarModelArrayList.add(new NumbarModel(id, number));
            }
            contactDbHelper.close();
            if (numbarModelArrayList.size() > 0) {
                readNumberAdapter.notifyDataSetChanged();
                numberCustomRecyclerView.setVisibility(View.VISIBLE);
                noIncomigTv.setVisibility(View.GONE);
            }
        }
        else {
            numbarModelArrayList.add(new NumbarModel(1, "sagar"));
            numberCustomRecyclerView.setVisibility(View.VISIBLE);
            noIncomigTv.setVisibility(View.GONE);
            readNumberAdapter.notifyDataSetChanged();
            Toast.makeText(this, "proble is with your insertion method now anukul bhai", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "read Success", Toast.LENGTH_SHORT).show();
    }
}
