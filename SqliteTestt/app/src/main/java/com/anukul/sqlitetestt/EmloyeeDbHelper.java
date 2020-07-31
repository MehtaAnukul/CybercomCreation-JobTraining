package com.anukul.sqlitetestt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.anukul.sqlitetestt.model.DepartmentModel;
import com.anukul.sqlitetestt.model.EmployeeModel;

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
        db.execSQL(EmployeeDbConstant.CREATE_EMPLOYEE_TABLE);
        db.execSQL(EmployeeDbConstant.CREATE_DEPARTMENT_TABLE);
        Log.e("Database","table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EmployeeDbConstant.CREATE_EMPLOYEE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EmployeeDbConstant.CREATE_DEPARTMENT_TABLE);

        onCreate(db);
    }

    public long insertEmpData(EmployeeModel employeeModel, SQLiteDatabase sqLiteDatabase){
        ContentValues cv = new ContentValues();
        cv.put(EmployeeDbConstant.EMPLOYEE_NAME, employeeModel.getEmployeeName());
        cv.put(EmployeeDbConstant.EMPLOYEE_ID, employeeModel.getEmpId());
        cv.put(EmployeeDbConstant.DEPARTMENT_ID,employeeModel.getDepartmentId());

        return sqLiteDatabase.insert(EmployeeDbConstant.EMPLOYEE_TABLE_NAME, null, cv);

    }
    public long insertDepartmentData(DepartmentModel departmentModel, SQLiteDatabase sqLiteDatabase){
        ContentValues cv = new ContentValues();
        cv.put(EmployeeDbConstant.DEPARTMENT_ID,departmentModel.getDepartmentId());
        cv.put(EmployeeDbConstant.DEPARTMENT_NAME, departmentModel.getDepartmentName());

        return sqLiteDatabase.insert(EmployeeDbConstant.DEPARTMENT_TABLE_NAME, null, cv);

    }

    //Update code
    /*public int updateUser(EmployeeModel employeeModel,SQLiteDatabase sqLiteDatabase) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EmployeeDbConstant.EMPLOYEE_NAME, employeeModel.getEmployeeName());

        // updating row
        return db.update(EmployeeDbConstant.EMPLOYEE_TABLE_NAME, values, employeeModel.getEmpId() + " = ?",
                new String[] { String.valueOf(employeeModel.getEmpId()) });
    }*/

    // Delete Code
   /* public boolean deleteItem(String name)
    {
        return sqLiteDatabase.delete(EmployeeDbConstant.EMPLOYEE_TABLE_NAME, EmployeeDbConstant.EMPLOYEE_DEPARTMENT + "=" + name, null) > 0;
    }*/


    //read data
    public ArrayList<EmployeeModel> getAllUser() {

        ArrayList<EmployeeModel> employeeModelArrayList = new ArrayList<>();
        String selectUserQuery = "SELECT * FROM " + EmployeeDbConstant.EMPLOYEE_TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(selectUserQuery,null);

        while (cursor.moveToNext()){
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setEmpId(cursor.getInt(cursor.getColumnIndex(EmployeeDbConstant.EMPLOYEE_ID)));
            employeeModel.setDepartmentId(cursor.getInt(cursor.getColumnIndex(EmployeeDbConstant.DEPARTMENT_ID)));
            employeeModel.setEmployeeName(cursor.getString(cursor.getColumnIndex(EmployeeDbConstant.EMPLOYEE_NAME)));

            employeeModelArrayList.add(employeeModel);
        }
        return employeeModelArrayList;

    }

    //read data
    public ArrayList<DepartmentModel> getAllDepartment() {

        ArrayList<DepartmentModel> departmentModelArrayList = new ArrayList<>();
        String selectUserQuery = "SELECT * FROM " + EmployeeDbConstant.DEPARTMENT_TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(selectUserQuery,null);

        while (cursor.moveToNext()){
            DepartmentModel departmentModel = new DepartmentModel();
            departmentModel.setDepartmentId(cursor.getInt(cursor.getColumnIndex(EmployeeDbConstant.DEPARTMENT_ID)));
            departmentModel.setDepartmentName(cursor.getString(cursor.getColumnIndex(EmployeeDbConstant.DEPARTMENT_NAME)));

            departmentModelArrayList.add(departmentModel);
        }
        return departmentModelArrayList;

    }
}
