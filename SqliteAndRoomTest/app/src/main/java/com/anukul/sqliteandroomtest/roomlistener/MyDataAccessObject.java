package com.anukul.sqliteandroomtest.roomlistener;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.anukul.sqliteandroomtest.roomModel.UserModel;

import java.util.List;

@Dao
public interface MyDataAccessObject {

    @Insert
    public void addUser(UserModel userModel);

    @Query("select * from users")
    public List<UserModel> getUsers();



}
