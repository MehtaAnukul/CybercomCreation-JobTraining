package com.anukul.serivcescustom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button startServicesBtn;
    private Button stopServicesBtn;

    private Intent servicesIntent;
    private boolean mstoploop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Anukul","MainActivity thread id:"+Thread.currentThread().getId());
        startServicesBtn = findViewById(R.id.activity_main_startServicesBtn);
        stopServicesBtn = findViewById(R.id.activity_main_stopServicesBtn);

        startServicesBtn.setOnClickListener(this);
        stopServicesBtn.setOnClickListener(this);

        servicesIntent = new Intent(getApplicationContext(),MyServices.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_startServicesBtn:
                mstoploop = true;
                startService(servicesIntent);
                break;
            case R.id.activity_main_stopServicesBtn:
                mstoploop = false;
                break;
        }
    }
}
