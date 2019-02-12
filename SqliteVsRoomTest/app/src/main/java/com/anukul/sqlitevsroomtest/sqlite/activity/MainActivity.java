package com.anukul.sqlitevsroomtest.sqlite.activity;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.anukul.sqlitevsroomtest.R;
import com.anukul.sqlitevsroomtest.sqlite.fragment.SplashFragment;
import com.anukul.sqlitevsroomtest.sqlite.fragment.SqliteRoomFragment;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Handler handler;
    private Runnable runnable;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar myActionBar = getSupportActionBar();

        //For hiding android actionbar

        Stetho.initializeWithDefaults(this);

        fragmentManager = getSupportFragmentManager();
        myActionBar.hide();
        SplashFragment splashFragment = new SplashFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_relativeContanier,splashFragment,MainActivity.class.getSimpleName());
        fragmentTransaction.commit();


        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                SqliteRoomFragment sqliteRoomFragment = new SqliteRoomFragment();
                myActionBar.show();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_relativeContanier,sqliteRoomFragment,MainActivity.class.getSimpleName());
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
