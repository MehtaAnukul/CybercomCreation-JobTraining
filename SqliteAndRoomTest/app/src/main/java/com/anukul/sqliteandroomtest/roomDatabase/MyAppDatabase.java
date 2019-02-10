package com.anukul.sqliteandroomtest.roomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.anukul.sqliteandroomtest.roomModel.UserModel;
import com.anukul.sqliteandroomtest.roomlistener.MyDataAccessObject;

@Database(entities = {UserModel.class}, version = 1)

public abstract class MyAppDatabase extends RoomDatabase {

    public abstract MyDataAccessObject mydataAccessObject();
}
