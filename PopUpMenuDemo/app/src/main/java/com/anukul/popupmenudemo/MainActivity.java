package com.anukul.popupmenudemo;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CoordinatorLayout coordinatorLayout;
    private Button popUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.activity_main_coordinatorLayout);
        popUpBtn = findViewById(R.id.activity_main_popUpMenuBtn);
        popUpBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);

        popupMenu.getMenuInflater().inflate(R.menu.whatsapp_manu,popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.whatsapp_manu_newGroup:
                        dispalyMessage("New Group");
                        break;
                    case R.id.whatsapp_manu_newBroadcast:
                        dispalyMessage("New Brodcast");
                        break;
                    case R.id.whatsapp_manu_whatsAppWeb:
                        dispalyMessage("WahtsApp Web");
                        break;
                    case R.id.whatsapp_manu_setting:
                        dispalyMessage("Setting");
                        break;
                }

                return false;
            }
        });
    }

    private void dispalyMessage(String message) {
        Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_SHORT).show();
    }
}
