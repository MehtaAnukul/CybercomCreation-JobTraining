package com.anukul.sqlitevsroomtest;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                fragmentManager = getSupportFragmentManager();

                SqliteRoomFragment sqliteRoomFragment = new SqliteRoomFragment();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.activity_main_relativeContanier,sqliteRoomFragment,MainActivity.class.getSimpleName());
                fragmentTransaction.commit();
            }
        };

        handler.postDelayed(runnable,3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);

    }

}
