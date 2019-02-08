package com.anukul.sqlitevsroomtest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class SqliteActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);


        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.appbar_menu_insertData:
                SqliteFragment sqliteFragment = new SqliteFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.activity_sqlite_fragment_container,sqliteFragment,SqliteActivity.class.getSimpleName());
                fragmentTransaction.commit();
                break;
            case R.id.appbar_menu_ViewData:
                ReadContactsFragment readContactsFragment = new ReadContactsFragment();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.add(R.id.activity_sqlite_fragment_container,readContactsFragment,SqliteActivity.class.getSimpleName());
                fragmentTransaction1.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
