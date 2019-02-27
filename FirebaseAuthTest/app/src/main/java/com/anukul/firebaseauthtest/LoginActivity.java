package com.anukul.firebaseauthtest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText loginEmailEd;
    private EditText loginPasswordEd;
    private Button loginBtn;
    private TextView signUpTv;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            final Intent gotoHome = new Intent(LoginActivity.this,
                    HomeActivity.class);
            startActivity(gotoHome);
            finish();
        }

        initView();

    }

    private void initView() {
        loginEmailEd = findViewById(R.id.activity_login_id_et);
        loginPasswordEd = findViewById(R.id.activity_login_password_et);
        loginBtn = findViewById(R.id.activity_login_loginbtn);
        signUpTv = findViewById(R.id.activity_login_signup_tv);

        loginBtn.setOnClickListener(this);
        signUpTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_login_loginbtn:
                userLogin();
                break;
            case R.id.activity_login_signup_tv:
                Intent gotoSignupActivity = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(gotoSignupActivity);
                break;

        }
    }

    private void userLogin() {
        final String loginEmail = loginEmailEd.getText().toString().trim();
        final String loginPassword = loginPasswordEd.getText().toString().trim();

        if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
            Toast.makeText(this, "Please Enter datails", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login SuccessFully Done", Toast.LENGTH_SHORT).show();

                                Intent gotoHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(gotoHomeActivity);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
