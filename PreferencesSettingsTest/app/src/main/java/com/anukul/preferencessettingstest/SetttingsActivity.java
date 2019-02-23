package com.anukul.preferencessettingstest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;


public class SetttingsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
   // private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setttings);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Settings");

        //fragmentManager = getSupportFragmentManager();

        frameLayout = findViewById(R.id.activity_settings_fragmentContainer);

        if (frameLayout != null) {
            if (savedInstanceState != null) {
                return;
            }

           /* FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.activity_settings_fragmentContainer,new SettingsFragment() , SetttingsActivity.class.getSimpleName());
            fragmentTransaction.commit();*/
           getFragmentManager().beginTransaction().add(R.id.activity_settings_fragmentContainer,new SettingsFragment()).commit();

        }

    }
}
