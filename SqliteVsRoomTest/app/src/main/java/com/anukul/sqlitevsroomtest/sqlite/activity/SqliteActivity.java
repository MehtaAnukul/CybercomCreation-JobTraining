package com.anukul.sqlitevsroomtest.sqlite.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anukul.sqlitevsroomtest.R;
import com.anukul.sqlitevsroomtest.sqlite.ContactDbHelper;
import com.anukul.sqlitevsroomtest.sqlite.adapter.ReadContactAdapter;
import com.anukul.sqlitevsroomtest.sqlite.fragment.SqlitReadContactFragment;
import com.anukul.sqlitevsroomtest.sqlite.fragment.SqliteAddFragment;
import com.anukul.sqlitevsroomtest.sqlite.model.ContactModel;

import java.util.ArrayList;

public class SqliteActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private FragmentManager fragmentManager;

    private ArrayList<ContactModel> contactModelArrayList;
    private ReadContactAdapter readContactAdapter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        ContactDbHelper contactDbHelper = new ContactDbHelper(SqliteActivity.this);
        contactModelArrayList = contactDbHelper.getAllUser();
        readContactAdapter = new ReadContactAdapter();


        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.appbar_menu_searchIcon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.appbar_menu_insertData:
                SqlitReadContactFragment sqlitReadContactFragmentt = new SqlitReadContactFragment();
                SqliteAddFragment sqliteAddFragment = new SqliteAddFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_sqlite_fragment_container, sqliteAddFragment, SqliteActivity.class.getSimpleName());
                //fragmentTransaction.addToBackStack(SqliteAddFragment.class.getSimpleName());
                fragmentTransaction.hide(sqlitReadContactFragmentt);
                fragmentTransaction.commit();
                break;
            case R.id.appbar_menu_ViewData:
                SqliteAddFragment sqliteAddFragmentt = new SqliteAddFragment();
                SqlitReadContactFragment sqlitReadContactFragment = new SqlitReadContactFragment();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.activity_sqlite_fragment_container, sqlitReadContactFragment, SqliteActivity.class.getSimpleName());
                //fragmentTransaction1.addToBackStack(ReadContactsFragment.class.getSimpleName());
                fragmentTransaction1.hide(sqliteAddFragmentt);
                fragmentTransaction1.commit();
                break;

            case R.id.appbar_menu_searchIcon:
                MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem menuItem) {
                        Toast.makeText(SqliteActivity.this, "Action View Expanded", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                        Toast.makeText(SqliteActivity.this, "Action View Collapsed", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                };

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String usertext = s.toLowerCase();
        ArrayList<ContactModel> searchArrayList = new ArrayList<>();

        for (int i = 0; i < SqlitReadContactFragment.getContactModelArrayList().size(); i++) {
            if (SqlitReadContactFragment.getContactModelArrayList().get(i).getName().toLowerCase().contains(usertext)
                    || SqlitReadContactFragment.getContactModelArrayList().get(i).getEmail().toLowerCase().contains(usertext)) {
                searchArrayList.add(SqlitReadContactFragment.getContactModelArrayList().get(i));
            }
        }
        Log.e("DOST1", SqlitReadContactFragment.getContactModelArrayList()+ "");
        Log.e("DOST2", searchArrayList + "");
        SqlitReadContactFragment.getReadContactAdapter().updateList(searchArrayList);
        return true;
    }
}
