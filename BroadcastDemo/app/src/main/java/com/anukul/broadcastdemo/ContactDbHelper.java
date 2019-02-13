package com.anukul.broadcastdemo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.anukul.broadcastdemo.app.ContactDbConstant;

import java.util.ArrayList;

public class ContactDbHelper extends SQLiteOpenHelper {

    private RecyclerView numberCustomRecyclerView;
    private ArrayList<NumbarModel> numbarModelArrayList;
    private ReadNumberAdapter readNumberAdapter;
    private TextView noIncomigTv;

    SQLiteDatabase sqLiteDatabase;

    public ContactDbHelper(@NonNull Context context) {
        super(context,ContactDbConstant.DATABASE_NAME,null,ContactDbConstant.DATABASE_VERSION);

        Log.e("DatabaseOperations","Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactDbConstant.CREATE_CONTACT_TABLE);
        Log.e("DatabaseOperations","Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactDbConstant.CONTACT_TABALE_NAME);
        onCreate(db);
    }

    //insert
    public void insertNumber(String number,SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();
       // contentValues.put(ContactDbConstant.CONTACT_COLUMN_ID,numbarModel.getId());
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_INCOMING_NO,number);
        sqLiteDatabase.insert(ContactDbConstant.CONTACT_TABALE_NAME,null,contentValues);
    }

    //read
    public Cursor getAllNumber(SQLiteDatabase db){
        ArrayList<NumbarModel> numbarModelArrayList = new ArrayList<>();
        String[] projection = {ContactDbConstant.CONTACT_COLUMN_ID,ContactDbConstant.CONTACT_COLUMN_INCOMING_NO};
        Cursor cursor = db.query(ContactDbConstant.CONTACT_TABALE_NAME,projection,null,null,null,null,null);

        return cursor;
    }
}
