package com.anukul.sqlitelogindemo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class ContactDbHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    public ContactDbHelper(@Nullable Context context) {
        super(context, ContactDbConstant.DATABASE_NAME, null, ContactDbConstant.DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();

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
    }

    public long insertContact(ContactModel contactModel, SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ContactDbConstant.CONTACT_COLUMN_NAME,contactModel.getName());
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_LASTNAME,contactModel.getLastName());
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_PHONE,contactModel.getPhoneNo());
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_EMAIL,contactModel.getEmail());
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_PASSWORD,contactModel.getPassword());

        return sqLiteDatabase.insert(ContactDbConstant.CONTACT_TABALE_NAME,null,contentValues);
       // Log.d("Database Operations","One row inserted Created");

    }

    //read data
   /* public Cursor readContact(SQLiteDatabase sqLiteDatabase){
        String projection[] = {ContactDbConstant.CONTACT_COLUMN_ID,ContactDbConstant.CONTACT_COLUMN_NAME,ContactDbConstant.CONTACT_COLUMN_EMAIL};

        Cursor cursor = sqLiteDatabase.query(ContactDbConstant.CONTACT_TABALE_NAME,projection,null,null,null,null,null);

        return cursor;
    }*/

   //read data
    public ArrayList<ContactModel> getAllUser() {

        ArrayList<ContactModel> contactModelArrayList = new ArrayList<>();
        String selectUserQuery = "SELECT * FROM " + ContactDbConstant.CONTACT_TABALE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(selectUserQuery,null);

        while (cursor.moveToNext()){
            ContactModel contactModel = new ContactModel();
            contactModel.setId(cursor.getInt(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_ID)));
            contactModel.setName(cursor.getString(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_NAME)));
            contactModel.setLastName(cursor.getString(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_LASTNAME)));
            contactModel.setEmail(cursor.getString(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_EMAIL)));
            contactModel.setPhoneNo(cursor.getString(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_PHONE)));
            contactModelArrayList.add(contactModel);
        }
        return contactModelArrayList;

    }

    public void updateContacts(ContactModel contactModel,int id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_NAME,contactModel.getName());
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_LASTNAME,contactModel.getLastName());
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_EMAIL,contactModel.getEmail());
        contentValues.put(ContactDbConstant.CONTACT_COLUMN_PHONE,contactModel.getPhoneNo());

        String selection = ContactDbConstant.CONTACT_COLUMN_ID+ " = "+id;

        sqLiteDatabase.update(ContactDbConstant.CONTACT_TABALE_NAME,contentValues,selection,null);
    }

    public  void deleteContacts(int id,SQLiteDatabase sqLiteDatabase){
        String selection = ContactDbConstant.CONTACT_COLUMN_ID+" = "+id;
        sqLiteDatabase.delete(ContactDbConstant.CONTACT_TABALE_NAME,selection,null);

    }



}
