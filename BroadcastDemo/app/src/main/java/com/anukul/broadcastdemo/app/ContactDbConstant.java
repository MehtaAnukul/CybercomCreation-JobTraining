package com.anukul.broadcastdemo.app;

public class ContactDbConstant {
    public static final String DATABASE_NAME = "number.db";
    public static final int DATABASE_VERSION = 1;

    public static final String CONTACT_TABALE_NAME = "incoming_info";
    public static final String CONTACT_COLUMN_ID = "contact_id";
    public static final String CONTACT_COLUMN_INCOMING_NO = "incoming_number";

    public static final String UPDATE_UI_FILTER = "com.anukul.broadcastdemo.UPDATE_UI";


    public static final String CREATE_CONTACT_TABLE =
            "CREATE TABLE " + CONTACT_TABALE_NAME + "("
            + CONTACT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CONTACT_COLUMN_INCOMING_NO + " TEXT)";


//CREATE TABLE TABLENAME ( COLUMN_NAME TYPE , COLUMN_NAME TYPE);
}
