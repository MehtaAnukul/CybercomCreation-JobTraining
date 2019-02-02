package com.anukul.contextmenu;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private Button contextMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.activity_main_coordinatorLayout);
        contextMenuBtn = findViewById(R.id.activity_main_contextMenuBtn);

        registerForContextMenu(contextMenuBtn);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.context_menu_addToPlaylist:
                Snackbar.make(coordinatorLayout,AppConstant.KEY_ADD,Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.context_menu_delete:
                Snackbar.make(coordinatorLayout,AppConstant.KEY_DELETE,Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.context_menu_share:
                Snackbar.make(coordinatorLayout,AppConstant.KEY_SHARE,Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.context_menu_report:
                Snackbar.make(coordinatorLayout,AppConstant.KEY_REPORT,Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.context_menu_trackInfo:
                Snackbar.make(coordinatorLayout,AppConstant.KEY_TRACK,Snackbar.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }
}
