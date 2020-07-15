package com.anukul.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class EmloyeeDbHelper extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;

    public EmloyeeDbHelper(@Nullable Context context) {
        super(context, EmployeeDbConstant.DATABASE_NAME ,null, EmployeeDbConstant.DATABASE_VERSION);
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
        cv.put(EmployeeDbConstant.EMPLOYEE_NAME, employeeModel.getEmployeeName());
        cv.put(EmployeeDbConstant.EMPLOYEE_DEPARTMENT, employeeModel.getDepartmentName());


        return sqLiteDatabase.insert(EmployeeDbConstant.EMPLOYEE_TABLE_NAME, null, cv);

    }

    //read data
    public ArrayList<EmployeeModel> getAllUser() {

        ArrayList<EmployeeModel> employeeModelArrayList = new ArrayList<>();
        String selectUserQuery = "SELECT * FROM " + EmployeeDbConstant.EMPLOYEE_TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(selectUserQuery,null);

        while (cursor.moveToNext()){
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(cursor.getInt(cursor.getColumnIndex(EmployeeDbConstant.EMPLOYEE_ID)));
            employeeModel.setDepartmentName(cursor.getString(cursor.getColumnIndex(EmployeeDbConstant.EMPLOYEE_DEPARTMENT)));
            employeeModel.setEmployeeName(cursor.getString(cursor.getColumnIndex(EmployeeDbConstant.EMPLOYEE_NAME)));

            employeeModelArrayList.add(employeeModel);
        }
        return employeeModelArrayList;

    }
}
