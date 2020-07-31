package com.anukul.sqlitetestt;

public class EmployeeDbConstant {
    public static final String DATABASE_NAME = "emp.db";
    public static final int DATABASE_VERSION = 1;

    public static final String EMPLOYEE_TABLE_NAME = "emp_info";
    public static final String EMPLOYEE_ID = "emp_id";
    public static final String EMPLOYEE_NAME = "emp_name";
   // public static final String EMPLOYEE_DEPARTMENT = "emp_department";

    public static final String DEPARTMENT_TABLE_NAME = "department_info";
    public static final String DEPARTMENT_ID = "department_id";
    public static final String DEPARTMENT_NAME = "department_name";



    public static final String CREATE_EMPLOYEE_TABLE =
            "CREATE TABLE " + EMPLOYEE_TABLE_NAME + "("
            + EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EMPLOYEE_NAME + " TEXT,"
            + DEPARTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"
            + ")";

    public static final String CREATE_DEPARTMENT_TABLE =
            "CREATE TABLE " + DEPARTMENT_TABLE_NAME + "("
                    + DEPARTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DEPARTMENT_NAME + " TEXT"
                    + ")";
}
