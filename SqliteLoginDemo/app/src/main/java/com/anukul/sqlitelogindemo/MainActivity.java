package com.anukul.sqlitelogindemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anukul.sqlitelogindemo.fragment.HomeFragment;
import com.anukul.sqlitelogindemo.fragment.ReadContactsFragment;
import com.anukul.sqlitelogindemo.fragment.UpdateFragment;
import com.anukul.sqlitelogindemo.listener.OnDbOprationListener;

public class MainActivity extends AppCompatActivity implements OnDbOprationListener {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                readContactsFragment();
                break;
            case 1:
                updateFragment();
                break;
        }
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
}

