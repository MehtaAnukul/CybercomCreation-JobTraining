package com.anukul.fragmentactivitycommunication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MessageReadListener{
    public static android.support.v4.app.FragmentManager fragmentManager;
    private FrameLayout nameFrameLayout;
    private TextView nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        nameFrameLayout = findViewById(R.id.activity_main_nameFramelayout);
        nameTv = findViewById(R.id.activity_main_nameTv);

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

    @Override
    public void onMessageRead(String message) {

        nameTv.setText(message);
    }
}
