package com.anukul.roomdemo.activity;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anukul.roomdemo.MyAppDatabase;
import com.anukul.roomdemo.R;
import com.anukul.roomdemo.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity  {
    private FragmentManager fragmentManager;
    public static MyAppDatabase myAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"userdb").allowMainThreadQueries().build();

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activtiy_main_framelayout_fragmentContainer,homeFragment,MainActivity.class.getSimpleName());
        fragmentTransaction.commit();
    }


}
