package com.anukul.sqliteandroomtest;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.anukul.sqliteandroomtest.roomDatabase.MyAppDatabase;
import com.anukul.sqliteandroomtest.roomfragment.RoomHomeFragment;

public class RoomActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private FragmentManager fragmentManager;
    public static MyAppDatabase myAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "userdb").allowMainThreadQueries().build();

        RoomHomeFragment roomHomeFragment = new RoomHomeFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_room_fragment_contanier,roomHomeFragment,RoomActivity.class.getSimpleName());
        fragmentTransaction.addToBackStack(RoomHomeFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }
}
