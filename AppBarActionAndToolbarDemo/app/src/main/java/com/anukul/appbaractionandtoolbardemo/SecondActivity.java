package com.anukul.appbaractionandtoolbardemo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button openThirdActivtyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        toolbar = findViewById(R.id.toolbar_layout_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        openThirdActivtyBtn = findViewById(R.id.activity_second_openThirdBtn);
        openThirdActivtyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoThirdActivity = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(gotoThirdActivity);
            }
        });
    }
}
