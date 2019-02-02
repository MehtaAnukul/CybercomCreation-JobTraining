package com.anukul.optionmenu;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class OptionMenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.appbar_menu_cast:
                displayMessage("Select Cast");
                break;
            case R.id.appbar_menu_search:
                displayMessage("Select Search");
                break;
            case R.id.appbar_menu_setting:
                displayMessage("Select Setting");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayMessage(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}
