package com.anukul.mellofood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText emailEd;
    private EditText passwordEd;
    private EditText confirmPasswordEd;
    private Button signUpBtn;
    private TextView returnTologinTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();
    }

    private void initView() {
        emailEd = findViewById(R.id.activity_signup_emailEd);
        passwordEd = findViewById(R.id.activity_signup_passwordEd);
        confirmPasswordEd = findViewById(R.id.activity_signup_confirmPasswordEd);
        signUpBtn = findViewById(R.id.activity_signup_signupBtn);
        returnTologinTv = findViewById(R.id.activity_signup_returnTologin);

        signUpBtn.setOnClickListener(this);
        returnTologinTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_signup_signupBtn:
                signUpBtnProcess();
                break;
            case R.id.activity_signup_returnTologin:
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void signUpBtnProcess() {
        final String email = emailEd.getText().toString().trim();
        final String password = passwordEd.getText().toString().trim();
        final String confirmPass = confirmPasswordEd.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
            Toast.makeText(this, "Please Enter the Details", Toast.LENGTH_SHORT).show();
        }else{
            final Intent gotoLoginActivityIntent = new Intent(SignupActivity.this,LoginActivity.class);
            gotoLoginActivityIntent.putExtra(AppConstant.KEY_EMAIL,email);
            gotoLoginActivityIntent.putExtra(AppConstant.KEY_PASSWORD,password);
            gotoLoginActivityIntent.putExtra(AppConstant.KEY_REPASSWORD,confirmPass);
            startActivity(gotoLoginActivityIntent);
            Toast.makeText(this, "SignUp done..", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
