package com.anukul.optionmenuruntime;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;

    private Button addShareBtn;
    private Boolean isItemAdd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.activity_main_coordinatorLayout);

        addShareBtn = findViewById(R.id.activity_main_addSharebtn);
        addShareBtn.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_addSharebtn:
                addShareOptionMenu();
                break;
        }
    }

    private void addShareOptionMenu() {
        if(!isItemAdd){
            isItemAdd = true;
            addShareBtn.setText("Remove Share Option");
            invalidateOptionsMenu();
        }else{
            isItemAdd = false;
            addShareBtn.setText("Add Share Option");
            invalidateOptionsMenu();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(isItemAdd){
            if(menu.findItem(AppConstant.KEY_SHARE_ITEM_ID) == null){
                MenuItem shareItem = menu.add(Menu.NONE,AppConstant.KEY_SHARE_ITEM_ID,5,"Share");
                shareItem.setIcon(R.drawable.ic_share);
                shareItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);

                shareItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        displayMessage("Share Option Selected");
                        return true;
                    }
                });
            }
        }else {
            menu.removeItem(AppConstant.KEY_SHARE_ITEM_ID);
        }
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    private void displayMessage(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}
