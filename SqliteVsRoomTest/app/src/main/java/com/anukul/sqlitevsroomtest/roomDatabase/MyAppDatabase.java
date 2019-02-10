package com.anukul.sqlitevsroomtest.roomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


import com.anukul.sqlitevsroomtest.roomModel.UserModel;
import com.anukul.sqlitevsroomtest.roomlistener.MyDataAccessObject;

@Database(entities = {UserModel.class}, version = 1)

public abstract class MyAppDatabase extends RoomDatabase {

    public abstract MyDataAccessObject mydataAccessObject();
}
