package com.anukul.fragmentruntimedemo;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        frameLayout = findViewById(R.id.activty_main_framelayout);

        if(frameLayout != null){
            if(savedInstanceState != null){
                return;
            }

            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.activty_main_framelayout,homeFragment,null);
            fragmentTransaction.commit();
        }




    }
}
