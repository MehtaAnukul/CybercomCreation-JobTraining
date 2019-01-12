package com.anukul.sqlitedemo;

public class ContactDbConstant {
    public static final String DATABASE_NAME = "contact.db";
    public static final int DATABASE_VERSION = 1;

    public static final String CONTACT_TABALE_NAME = "contact_info";
    public static final String CONTACT_ID = "contact_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";

    public static final String CREATE_CONTACT_TABLE =
            "CREATE TABLE" +CONTACT_TABALE_NAME+ "("
            +CONTACT_ID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"
            +NAME+ "TEXT,"
            +EMAIL+ "TEXT"
            +")";

//CREATE TABLE TABLENAME ( COLUMN_NAME TYPE , COLUMN_NAME TYPE);
}
