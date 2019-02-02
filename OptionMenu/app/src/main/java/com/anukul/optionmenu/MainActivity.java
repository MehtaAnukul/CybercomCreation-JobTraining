package com.anukul.optionmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends OptionMenuActivity {
    private Toolbar toolbar;
    private Button clickBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        clickBtn = findViewById(R.id.activitiy_main_clickBtn);
        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoSecondActivity = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(gotoSecondActivity);
            }
        });

    }

   /* @Override
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
    }*/
}
