package com.anukul.navigationdrawerdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_navigation);

        drawerLayout = findViewById(R.id.activity_main_drawerLayout);
        navigationView = findViewById(R.id.activity_main_navigationView);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.drawer_menu_person:
                menuItem.setChecked(true);
                displayMessage("Person selected");
                drawerLayout.closeDrawers();
                break;
            case R.id.drawer_menu_email:
                menuItem.setChecked(true);
                displayMessage("Email selected");
                drawerLayout.closeDrawers();
                break;
            case R.id.drawer_menu_camera:
                menuItem.setChecked(true);
                displayMessage("Camera selected");
                drawerLayout.closeDrawers();
                break;
            case R.id.drawer_menu_logout:
                menuItem.setChecked(true);
                displayMessage("logout selected");
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }

    private void displayMessage(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
