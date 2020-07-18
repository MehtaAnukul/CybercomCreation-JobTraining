package com.anukul.sqlitetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private FragmentManager fragmentManager;
    private ArrayList<EmployeeModel> employeeModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        EmloyeeDbHelper emloyeeDbHelper = new EmloyeeDbHelper(MainActivity.this);
        employeeModelArrayList = emloyeeDbHelper.getAllUser();

        fragmentManager = getSupportFragmentManager();

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_background);

        drawerLayout = findViewById(R.id.activity_main_drawerLayout);
        navigationView = findViewById(R.id.activity_main_navigationView);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.drawer_menu_add_deparment:
                menuItem.setChecked(true);
                AddEmployeeFragment addEmployeeFragmentt = new AddEmployeeFragment();
                AddDepartmentFragment addDepartmentFragment = new AddDepartmentFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_fragment_container, addDepartmentFragment, MainActivity.class.getSimpleName());
                //fragmentTransaction.addToBackStack(SqliteAddFragment.class.getSimpleName());
                fragmentTransaction.hide(addEmployeeFragmentt);
                fragmentTransaction.commit();
                displayMessage("Add Deparment");
                drawerLayout.closeDrawers();
                break;
            case R.id.drawer_menu_add_employe:
                menuItem.setChecked(true);
                AddDepartmentFragment addDepartmentFragmentt = new AddDepartmentFragment();
                AddEmployeeFragment addEmployeeFragment = new AddEmployeeFragment();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.activity_main_fragment_container, addEmployeeFragment, MainActivity.class.getSimpleName());
                //fragmentTransaction.addToBackStack(SqliteAddFragment.class.getSimpleName());
                fragmentTransaction1.hide(addDepartmentFragmentt);
                fragmentTransaction1.commit();

                displayMessage("Add Employee");
                drawerLayout.closeDrawers();
                break;
            case R.id.drawer_menu_emp_details:
                menuItem.setChecked(true);
                AddEmployeeFragment addEmployeeFragmenttt = new AddEmployeeFragment();
                AddDepartmentFragment addDepartmentFragmenttt = new AddDepartmentFragment();
                EmployeeDetailsFragment employeeDetailsFragment = new EmployeeDetailsFragment();
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                fragmentTransaction2.replace(R.id.activity_main_fragment_container,employeeDetailsFragment ,MainActivity.class.getSimpleName());
                fragmentTransaction2.hide(addDepartmentFragmenttt);
                fragmentTransaction2.hide(addEmployeeFragmenttt);
                fragmentTransaction2.commit();
                displayMessage("Employee Details");
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }
    private void displayMessage(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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