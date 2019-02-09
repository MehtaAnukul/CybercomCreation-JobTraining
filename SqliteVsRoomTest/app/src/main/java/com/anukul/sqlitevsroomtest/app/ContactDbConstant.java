package com.anukul.sqlitevsroomtest.app;

public class ContactDbConstant {
    public static final String DATABASE_NAME = "contact.db";
    public static final int DATABASE_VERSION = 1;

    public static final String CONTACT_TABALE_NAME = "contact_info";
    public static final String CONTACT_COLUMN_ID = "contact_id";
    public static final String CONTACT_COLUMN_NAME = "name";
    public static final String CONTACT_COLUMN_LASTNAME = "lastName";
    public static final String CONTACT_COLUMN_PHONE_NO = "phoneNo";
    public static final String CONTACT_COLUMN_EMAIL = "email";


    public static final String CREATE_CONTACT_TABLE =
            "CREATE TABLE " + CONTACT_TABALE_NAME + "("
            + CONTACT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CONTACT_COLUMN_NAME + " TEXT,"
            + CONTACT_COLUMN_LASTNAME + " TEXT,"
            + CONTACT_COLUMN_PHONE_NO + " NUMBER,"
            + CONTACT_COLUMN_EMAIL + " TEXT"
            + ")";

//CREATE TABLE TABLENAME ( COLUMN_NAME TYPE , COLUMN_NAME TYPE);
}
