package com.anukul.fragmentlifecycle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FrameLayout firstFrameLayout;
    private FrameLayout secondFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fragmentManager = getSupportFragmentManager();
        firstFrameLayout = findViewById(R.id.activity_main_firstFrameLayout);

        if(firstFrameLayout != null){
            if(savedInstanceState != null){
                return;
            }
            FirstFragment firstFragment = new FirstFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.activity_main_firstFrameLayout,firstFragment,MainActivity.class.getSimpleName());
            fragmentTransaction.commit();
        }

    }
}
