package com.anukul.roomdemo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyDataAccessObject {

    @Insert
    public void addUser(UserModel userModel);

    @Query("select * from users")
    public List<UserModel> getUsers();

    @Delete
    public void deleteUser(UserModel userModel);

    @Update
    public void updateUser(UserModel userModel);


}
