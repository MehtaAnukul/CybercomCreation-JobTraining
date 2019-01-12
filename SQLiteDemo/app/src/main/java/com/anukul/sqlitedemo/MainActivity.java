package com.anukul.sqlitedemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements OnDbOprationListener{
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main_frameLayout_fragment_container,homeFragment,MainActivity.class.getSimpleName());
        fragmentTransaction.commit();

    }


    @Override
    public void dbOpPerformed(int id) {
        switch (id){
            case 0:
                AddContactFragment addContactFragment = new AddContactFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_frameLayout_fragment_container,addContactFragment,MainActivity.class.getSimpleName());
                fragmentTransaction.addToBackStack(MainActivity.class.getSimpleName());
                fragmentTransaction.commit();
                break;
        }
    }
}
