package com.anukul.sqliteandroomtest;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

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

        fragmentManager = getSupportFragmentManager();

        SplashFragment splashFragment = new SplashFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fragment_contanier,splashFragment,SplashFragment.class.getSimpleName());
        fragmentTransaction.commit();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                MainFragment mainFragment = new MainFragment();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.activity_main_fragment_contanier,mainFragment,MainFragment.class.getSimpleName());
                fragmentTransaction1.commit();
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
