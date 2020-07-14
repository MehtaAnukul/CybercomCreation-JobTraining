package com.anukul.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class EmloyeeDbHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    public EmloyeeDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, EmployeeDbConstant.DATABASE_NAME, factory, EmployeeDbConstant.DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();

        Log.e("Database","Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EmployeeDbConstant.CREATE_CONTACT_TABLE);
        Log.e("Database","table created");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EmployeeDbConstant.CREATE_CONTACT_TABLE);
    }

    public long insertEmpData(EmployeeModel employeeModel,SQLiteDatabase sqLiteDatabase){
        ContentValues cv = new ContentValues();
        cv.put(EmployeeDbConstant.EMPLOYEE_NAME, employeeModel.getAddEmployee());
        cv.put(EmployeeDbConstant.EMPLOYEE_DEPARTMENT, employeeModel.getAddDepartment());


        return sqLiteDatabase.insert(EmployeeDbConstant.EMPLOYEE_TABLE_NAME, null, cv);

    }
}
