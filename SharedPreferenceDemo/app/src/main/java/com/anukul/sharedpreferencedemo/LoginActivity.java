package com.anukul.sharedpreferencedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferenceConfig sharedPreferenceConfig;

    private EditText nameEd;
    private EditText passwordEd;
    private Button loginBtn;

    private String systemUserName = AppConstent.KEY_USER_NAME;
    private String systemPassword = AppConstent.KEY_USER_PASSWORD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Stetho.initializeWithDefaults(this);

        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        initView();


        //if its return true so user login already
        if (sharedPreferenceConfig.readLoginStatus()) {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
    }

    private void initView() {
        nameEd = findViewById(R.id.activity_login_nameEd);
        passwordEd = findViewById(R.id.activity_login_passwordEd);
        loginBtn = findViewById(R.id.activity_login_loginBtn);

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_loginBtn:
                loginProcess();
                break;
        }
    }

    private void loginProcess() {
        //final String systemUserName = getResources().getString(R.string.user_name);
       // final String systemPassword = getResources().getString(R.string.user_password);

        final String name = nameEd.getText().toString().trim();
        final String password = passwordEd.getText().toString().trim();

        if (name.equals(systemUserName) && password.equals(systemPassword)) {
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
            Intent gotoWelcomeActivity = new Intent(this, WelcomeActivity.class);
            // gotoWelcomeActivity.putExtra(AppConstent.KEY_NAME,systemUserName);
            startActivity(gotoWelcomeActivity);
            sharedPreferenceConfig.writeLoginStatus(true);
            finish();

        } else {
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
            nameEd.setText("");
            passwordEd.setText("");
        }
    }
}
