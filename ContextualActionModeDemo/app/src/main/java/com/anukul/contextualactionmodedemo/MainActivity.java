package com.anukul.contextualactionmodedemo;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button contextualActionBtn;
    private CoordinatorLayout coordinatorLayout;

    private ActionMode actionMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contextualActionBtn = findViewById(R.id.activity_main_openContextualModeBtn);
        coordinatorLayout = findViewById(R.id.activity_main_coordinatorLayout);

        contextualActionBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(actionMode != null) {
                    return false;
                }
                actionMode = startActionMode(callback);
                return true;
            }
        });
    }

    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

            getMenuInflater().inflate(R.menu.contextual_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {
           switch (menuItem.getItemId()){
               case R.id.contextual_menu_delete:
                   displayMessage("Delete Option selected");
                   mode.finish();
                   break;
               case R.id.contextual_menu_share:
                   displayMessage("Share Option selected");
                   mode.finish();
                   break;
           }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    private void displayMessage(String message) {
        Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_SHORT).show();
    }

}
