package com.anukul.mellofood.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anukul.mellofood.AppConstant;
import com.anukul.mellofood.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText loginEmailEd;
    private EditText loginPasswordEd;
    private Button loginBtn;
    private Button signupBtn;
    private TextView skipTv;
    private ImageView showPasswordIv;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        intent = getIntent();
    }

    private void initView() {
        loginEmailEd = findViewById(R.id.activity_login_emailEd);
        loginPasswordEd = findViewById(R.id.activity_login_passwordEd);
        loginBtn = findViewById(R.id.activty_login_loginBtn);
        signupBtn = findViewById(R.id.activty_login_signupBtn);
        skipTv = findViewById(R.id.activity_login_skipTv);
        showPasswordIv = findViewById(R.id.activty_login_showPasswordIv);

        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        skipTv.setOnClickListener(this);
        showPasswordIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activty_login_loginBtn:
                loginProcess();
                break;
            case R.id.activty_login_signupBtn:
                Intent intent = new Intent(this,SignupActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.activity_login_skipTv:
                Intent gotoOutLets = new Intent(this,OutletActivity.class);
                startActivity(gotoOutLets);
                finish();
                break;
            case R.id.activty_login_showPasswordIv:
                showPasswordIvProcess();
                break;
        }
    }

    private void showPasswordIvProcess() {
        if(loginPasswordEd.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
            loginPasswordEd.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showPasswordIv.setImageResource(R.drawable.passwordshow);
        }else {
            loginPasswordEd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            showPasswordIv.setImageResource(R.drawable.passwordhide);
        }
    }

    private void loginProcess() {
        String loginEmail = loginEmailEd.getText().toString().trim();
        String loginPassword = loginPasswordEd.getText().toString().trim();

        String signupEmail = intent.getStringExtra(AppConstant.KEY_EMAIL);
        String signupPassword = intent.getStringExtra(AppConstant.KEY_PASSWORD);

        if(loginEmail.isEmpty() || loginPassword.isEmpty()){
            Toast.makeText(this, "Please Enter the Details", Toast.LENGTH_SHORT).show();
        }else {

            if(loginEmail.equals(signupEmail) && loginPassword.equals(signupPassword)){
                Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(LoginActivity.this, "EmailID or Password invalid", Toast.LENGTH_SHORT).show();
            }
        }

        loginEmailEd.setText("");
        loginPasswordEd.setText("");



    }
}
