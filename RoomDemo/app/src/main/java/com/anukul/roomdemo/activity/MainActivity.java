package com.anukul.roomdemo.activity;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.anukul.roomdemo.MyAppDatabase;
import com.anukul.roomdemo.R;
import com.anukul.roomdemo.fragment.HomeFragment;
import com.anukul.roomdemo.fragment.ReadUserFragment;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    public static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        fragmentManager = getSupportFragmentManager();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "userdb").allowMainThreadQueries().build();

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activtiy_main_framelayout_fragmentContainer, homeFragment, MainActivity.class.getSimpleName());
        fragmentTransaction.addToBackStack(HomeFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }


}
