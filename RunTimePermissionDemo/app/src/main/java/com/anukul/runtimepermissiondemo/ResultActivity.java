package com.anukul.runtimepermissiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView permissionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        permissionTv = findViewById(R.id.activity_result_permissionTv);

        if(getIntent().getExtras()!=null){
            String message = getIntent().getExtras().getString(AppConstant.KEY_MESSAGE);
            permissionTv.setText(message);
        }
    }
}
