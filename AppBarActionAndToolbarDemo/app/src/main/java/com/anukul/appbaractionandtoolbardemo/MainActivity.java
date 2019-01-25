package com.anukul.appbaractionandtoolbardemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button openSecondActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    toolbar = findViewById(R.id.toolbar_layout_toolbar);
    openSecondActivityBtn = findViewById(R.id.activity_main_openSecondBtn);
    openSecondActivityBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent gotoSecondActivity = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(gotoSecondActivity);
        }
    });

    setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.appbar_menu_shareIcon:
                Toast.makeText(this, "Share Option Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.appbar_menu_setting:
                Toast.makeText(this, "Setting Option Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
