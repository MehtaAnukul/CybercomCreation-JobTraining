package com.anukul.alertusingdialogfragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NewCarDialog.CarsSelectionListener {
    private Button fireMissileAlertBtn;
    private Button colorAlertBtn;
    private Button carAlertbtn;
    private Button genderAlertBtn;
    private Button customAlertBtn;
    private Button carSetTvAlertBtn;

    private TextView helloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        fireMissileAlertBtn = findViewById(R.id.activity_main_fireMissileAlertBtn);
        colorAlertBtn = findViewById(R.id.activity_main_colorAlertBtn);
        carAlertbtn = findViewById(R.id.activity_main_carAlertBtn);
        genderAlertBtn = findViewById(R.id.activity_main_genderAlertBtn);
        customAlertBtn = findViewById(R.id.activity_main_customAlertBtn);
        carSetTvAlertBtn = findViewById(R.id.activity_main_carWithTvAlertBtn);

        helloTv = findViewById(R.id.activity_main_helloTv);

        fireMissileAlertBtn.setOnClickListener(this);
        colorAlertBtn.setOnClickListener(this);
        carAlertbtn.setOnClickListener(this);
        genderAlertBtn.setOnClickListener(this);
        customAlertBtn.setOnClickListener(this);
        carSetTvAlertBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_fireMissileAlertBtn:
                new FireMissileDialog().show(getSupportFragmentManager(), "missile");
                break;
            case R.id.activity_main_colorAlertBtn:
                new MyDialog().show(getSupportFragmentManager(), "Color");
                break;
            case R.id.activity_main_carAlertBtn:
                new CarDialog().show(getSupportFragmentManager(), "car");
                break;
            case R.id.activity_main_genderAlertBtn:
                new GenderDialog().show(getSupportFragmentManager(), "gender");
                break;
            case R.id.activity_main_customAlertBtn:
                CustomDialog customDialog = new CustomDialog();
                customDialog.show(getSupportFragmentManager(), "custom");
                break;
            case R.id.activity_main_carWithTvAlertBtn:
                NewCarDialog newCarDialog = new NewCarDialog();
                newCarDialog.show(getSupportFragmentManager(), "newCar");
                break;
        }
    }

    @Override
    public void onCarSelected(ArrayList<String> finalCarArrayList) {
        String finalCarName = "";

        for (int i = 0; i < finalCarArrayList.size(); i++) {
            finalCarName = finalCarName + "\n" + finalCarArrayList.get(i);
        }
        helloTv.setText(finalCarName);
    }
}
