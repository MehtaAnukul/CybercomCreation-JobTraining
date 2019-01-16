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
            case 1:
                ReadContactsFragment readContactsFragment = new ReadContactsFragment();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.activity_main_frameLayout_fragment_container,readContactsFragment,MainActivity.class.getSimpleName());
                fragmentTransaction1.addToBackStack(MainActivity.class.getSimpleName());
                fragmentTransaction1.commit();
                break;
            case 2:
                UpdateFragment updateFragment = new UpdateFragment();
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                fragmentTransaction2.replace(R.id.activity_main_frameLayout_fragment_container,updateFragment,MainActivity.class.getSimpleName());
                fragmentTransaction2.addToBackStack(MainActivity.class.getSimpleName());
                fragmentTransaction2.commit();
                break;
            case 3:
                DeleteContactFragment deleteContactFragment = new DeleteContactFragment();
                FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                fragmentTransaction3.replace(R.id.activity_main_frameLayout_fragment_container,deleteContactFragment);
                fragmentTransaction3.addToBackStack(MainActivity.class.getSimpleName());
                fragmentTransaction3.commit();
                break;
        }
    }
}
