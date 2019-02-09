package com.anukul.sqliteandroomtest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.anukul.sqliteandroomtest.listener.OnDbOprationListener;

public class SqliteActivity extends AppCompatActivity implements OnDbOprationListener {
    private Toolbar toolbar;

    private FragmentManager fragmentManager;

    SqliteHomeFragment sqliteHomeFragment = new SqliteHomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_sqlite_fragment_contanier,sqliteHomeFragment,SqliteHomeFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public void dbOpPerformed(int id) {
        switch (id){
            case 0:
                sqliteAddContactFragment();
                break;
            case 1:
                sqliteViewContactFragment();
                break;
        }
    }

    private void sqliteViewContactFragment() {
        SqliteReadContactFragment sqliteReadContactFragment = new SqliteReadContactFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_sqlite_fragment_contanier,sqliteReadContactFragment,SqliteReadContactFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(SqliteHomeFragment.class.getSimpleName());
        //fragmentTransaction.hide(sqliteHomeFragment);
        fragmentTransaction.commit();

    }

    private void sqliteAddContactFragment() {
        SqliteAddFragment sqliteAddFragment = new SqliteAddFragment();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.replace(R.id.activity_sqlite_fragment_contanier,sqliteAddFragment,SqliteAddFragment.class.getSimpleName());
        fragmentTransaction1.addToBackStack(SqliteHomeFragment.class.getSimpleName());
       // fragmentTransaction.hide(sqliteHomeFragment);
        fragmentTransaction1.commit();
    }
}
