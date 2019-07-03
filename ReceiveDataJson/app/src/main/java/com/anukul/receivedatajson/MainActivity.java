package com.anukul.receivedatajson;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button clickBtn;
    public static TextView jsonDataTv;

    public static ProgressDialog progressDialog;

    private FetchDataBGTask fetchDataBGTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        clickBtn = findViewById(R.id.activity_main_btn);
        jsonDataTv = findViewById(R.id.activity_main_tv);

        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataBGTask = new FetchDataBGTask();
                fetchDataBGTask.execute();
            }
        });

    }


}
