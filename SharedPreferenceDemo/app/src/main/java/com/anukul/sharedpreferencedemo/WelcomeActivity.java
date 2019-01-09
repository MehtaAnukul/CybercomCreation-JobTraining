package com.anukul.sharedpreferencedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private SharedPreferenceConfig sharedPreferenceConfig;
    private TextView welcomeUserTv;
    private Button logoutBtn;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        intent = getIntent();

        welcomeUserTv = findViewById(R.id.activity_welcome_welcomeTv);
        logoutBtn = findViewById(R.id.activity_welcome_logoutBtn);

        final String name = getResources().getString(R.string.user_name);
        welcomeUserTv.setText("Welcome "+name);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceConfig.writeLoginStatus(false);
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                finish();
            }
        });


    }
}
