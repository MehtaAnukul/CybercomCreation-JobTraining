package com.anukul.sqlitetest;

public class EmployeeDbConstant {
    public static final String DATABASE_NAME = "emp.db";
    public static final int DATABASE_VERSION = 1;

    public static final String EMPLOYEE_TABLE_NAME = "emp_info";
    public static final String EMPLOYEE_ID = "emp_id";
    public static final String EMPLOYEE_NAME = "name";
    public static final String EMPLOYEE_DEPARTMENT = "name";
    //public static final String EMPLOYEE_NAME = "name";

    public static final String CREATE_CONTACT_TABLE =
            "CREATE TABLE " + EMPLOYEE_TABLE_NAME + "("
            + EMPLOYEE_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EMPLOYEE_NAME + " TEXT, "
            + EMPLOYEE_DEPARTMENT + " TEXT, "
            + ")";
}
