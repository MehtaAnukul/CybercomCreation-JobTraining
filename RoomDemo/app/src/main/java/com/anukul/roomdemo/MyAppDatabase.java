package com.anukul.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.anukul.roomdemo.listener.MyDataAccessObject;
import com.anukul.roomdemo.model.UserModel;

@Database(entities = {UserModel.class}, version = 1)

public abstract class MyAppDatabase extends RoomDatabase {

    public abstract MyDataAccessObject mydataAccessObject();
}
