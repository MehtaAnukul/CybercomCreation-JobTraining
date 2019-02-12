package com.anukul.broadcastdemo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import com.anukul.broadcastdemo.app.ContactDbConstant;

import java.util.ArrayList;

public class ContactDbHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    public ContactDbHelper(@NonNull Context context) {
        super(context,ContactDbConstant.DATABASE_NAME,null,ContactDbConstant.DATABASE_VERSION);

        Log.e("DatabaseOperations","Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactDbConstant.CONTACT_TABALE_NAME);
        Log.e("DatabaseOperations","Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactDbConstant.CONTACT_TABALE_NAME);
        onCreate(db);
    }

    //insert
    public long insertNumber(NumbarModel numbarModel,SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_ID,numbarModel.getId());
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_INCOMING_NO,numbarModel.getNumber());
       return sqLiteDatabase.insert(ContactDbConstant.CONTACT_TABALE_NAME,null,contentValues);
    }

    //read
    public ArrayList<NumbarModel> getAllNumber(){
        ArrayList<NumbarModel> numbarModelArrayList = new ArrayList<>();
        //String selectUserQuery = "SELECT * FROM " + ContactDbConstant.CONTACT_TABALE_NAME;
        String selectUserQuery = "SELECT * FROM " + ContactDbConstant.CONTACT_TABALE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(selectUserQuery,null);

        while (cursor.moveToNext()){
            NumbarModel numbarModel = new NumbarModel();
            numbarModel.setId(cursor.getInt(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_ID)));
            numbarModel.setNumber(cursor.getString(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_INCOMING_NO)));
            numbarModelArrayList.add(numbarModel);
        }
        cursor.close();

        return numbarModelArrayList;
    }




}
