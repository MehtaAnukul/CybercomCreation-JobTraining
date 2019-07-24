package com.anukul.sqlitedemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.anukul.sqlitedemo.R;
import com.anukul.sqlitedemo.fragment.AddContactFragment;
import com.anukul.sqlitedemo.fragment.DeleteContactFragment;
import com.anukul.sqlitedemo.fragment.HomeFragment;
import com.anukul.sqlitedemo.fragment.ReadContactsFragment;
import com.anukul.sqlitedemo.fragment.UpdateFragment;
import com.anukul.sqlitedemo.listener.OnDbOprationListener;
//import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity implements OnDbOprationListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Stetho.initializeWithDefaults(this);

        fragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_main_frameLayout_fragment_container, homeFragment, MainActivity.class.getSimpleName());
        fragmentTransaction.commit();

    }


    @Override
    public void dbOpPerformed(int id) {
        switch (id) {
            case 0:
                addContentFragment();
                break;
            case 1:
                readContactsFragment();
                break;
            case 2:
                updateFragment();
                break;
            case 3:
                deleteContactFragment();
                break;
        }
    }

    private void deleteContactFragment() {
        DeleteContactFragment deleteContactFragment = new DeleteContactFragment();
        FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
        fragmentTransaction3.replace(R.id.activity_main_frameLayout_fragment_container, deleteContactFragment);
        fragmentTransaction3.addToBackStack(MainActivity.class.getSimpleName());
        fragmentTransaction3.commit();
    }

    private void updateFragment() {
        UpdateFragment updateFragment = new UpdateFragment();
        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.replace(R.id.activity_main_frameLayout_fragment_container, updateFragment, MainActivity.class.getSimpleName());
        fragmentTransaction2.addToBackStack(MainActivity.class.getSimpleName());
        fragmentTransaction2.commit();
    }

    private void readContactsFragment() {
        ReadContactsFragment readContactsFragment = new ReadContactsFragment();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.replace(R.id.activity_main_frameLayout_fragment_container, readContactsFragment, MainActivity.class.getSimpleName());
        fragmentTransaction1.addToBackStack(MainActivity.class.getSimpleName());
        fragmentTransaction1.commit();
    }

    private void addContentFragment() {
        AddContactFragment addContactFragment = new AddContactFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_frameLayout_fragment_container, addContactFragment, MainActivity.class.getSimpleName());
        fragmentTransaction.addToBackStack(MainActivity.class.getSimpleName());
        fragmentTransaction.commit();
    }
}
