package com.anukul.optionmenuruntime;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.activity_main_coordinatorLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.appbar_menu_search:
                Snackbar.make(coordinatorLayout, AppConstant.KEY_SEARCH, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.appbar_menu_cast:
                Snackbar.make(coordinatorLayout, AppConstant.KEY_CAST, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.appbar_menu_setting:
                Snackbar.make(coordinatorLayout, AppConstant.KEY_SETTING, Snackbar.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}
