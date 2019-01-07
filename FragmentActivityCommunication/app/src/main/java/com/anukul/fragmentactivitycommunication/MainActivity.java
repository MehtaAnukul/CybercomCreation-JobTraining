package com.anukul.fragmentactivitycommunication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    public static android.support.v4.app.FragmentManager fragmentManager;
    private FrameLayout nameFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        nameFrameLayout = findViewById(R.id.activity_main_nameFramelayout);

        if (nameFrameLayout != null){
            if(savedInstanceState != null){
                return;
            }

            MessageFragment messageFragment = new MessageFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.activity_main_nameFramelayout,messageFragment,null);
            fragmentTransaction.commit();

        }


    }
}
