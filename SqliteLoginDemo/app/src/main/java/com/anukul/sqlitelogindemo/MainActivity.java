package com.anukul.sqlitelogindemo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.anukul.sqlitelogindemo.fragment.DeleteContactFragment;
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
            case 2:
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.appbar_menu_logout:
                final Intent gotoLogin = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(gotoLogin);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

