package com.anukul.sqlitelogindemo;

public class ContactDbConstant {
    public static final String DATABASE_NAME = "contact.db";
    public static final int DATABASE_VERSION = 1;

    public static final String CONTACT_TABALE_NAME = "contact_info";
    public static final String CONTACT_COLUMN_ID = "contact_id";
    public static final String CONTACT_COLUMN_NAME = "name";
    public static final String CONTACT_COLUMN_LASTNAME = "lastname";
    public static final String CONTACT_COLUMN_PHONE = "phone";
    public static final String CONTACT_COLUMN_EMAIL = "email";
    public static final String CONTACT_COLUMN_PASSWORD = "password";
    public static final String CONTACT_COLUMN_GENDER = "gender";

    public static final String CREATE_CONTACT_TABLE =
            "CREATE TABLE " + CONTACT_TABALE_NAME + "("
            + CONTACT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CONTACT_COLUMN_NAME + " TEXT,"
            + CONTACT_COLUMN_LASTNAME + " TEXT,"
            + CONTACT_COLUMN_PHONE + " TEXT,"
            + CONTACT_COLUMN_EMAIL + " TEXT,"
            + CONTACT_COLUMN_PASSWORD + " TEXT,"
            + CONTACT_COLUMN_GENDER + " TEXT"
            + ")";

//CREATE TABLE TABLENAME ( COLUMN_NAME TYPE , COLUMN_NAME TYPE);
}
