package com.anukul.sqlitedemo;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class ContactDbHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    public ContactDbHelper(@Nullable Context context) {
        super(context, ContactDbConstant.DATABASE_NAME, null, ContactDbConstant.DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
        Log.d("Database Operations","Databae Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactDbConstant.CREATE_CONTACT_TABLE);
        Log.d("Database Operations","Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactDbConstant.CONTACT_TABALE_NAME);
    }

    public long insertContact(ContactModel contactModel){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ContactDbConstant.NAME,contactModel.getName());
        contentValues.put(ContactDbConstant.EMAIL,contactModel.getEmail());

        return sqLiteDatabase.insert(ContactDbConstant.CONTACT_TABALE_NAME,null,contentValues);
       // Log.d("Database Operations","One row inserted Created");

    }

}
