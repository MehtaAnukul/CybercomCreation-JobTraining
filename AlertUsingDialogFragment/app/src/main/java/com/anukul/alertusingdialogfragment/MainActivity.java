package com.anukul.alertusingdialogfragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button fireMissileAlertBtn;
    private Button colorAlertBtn;
    private Button catAlertbtn;
    private Button genderAlertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fireMissileAlertBtn = findViewById(R.id.activity_main_fireMissileAlertBtn);
        colorAlertBtn = findViewById(R.id.activity_main_colorAlertBtn);
        catAlertbtn = findViewById(R.id.activity_main_carAlertBtn);
        genderAlertBtn = findViewById(R.id.activity_main_genderAlertBtn);

        fireMissileAlertBtn.setOnClickListener(this);
        colorAlertBtn.setOnClickListener(this);
        catAlertbtn.setOnClickListener(this);
        genderAlertBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_fireMissileAlertBtn:
                new FireMissileDialog().show(getSupportFragmentManager(), "missile");
                break;
            case R.id.activity_main_colorAlertBtn:
                new MyDialog().show(getSupportFragmentManager(),"Color");
                break;
            case R.id.activity_main_carAlertBtn:
                new CarDialog().show(getSupportFragmentManager(),"car");
                break;
            case R.id.activity_main_genderAlertBtn:
                new GenderDialog().show(getSupportFragmentManager(),"gender");
                break;
        }
    }
}
