package com.anukul.retrofitlogindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private EditText loginEmailEd;
    private EditText loginPasswordEd;
    private Button loginBtn;
    private TextView signUpTv;

    UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmailEd = findViewById(R.id.activity_login_emailEd);
        loginPasswordEd = findViewById(R.id.activity_login_passwordEd);
        loginBtn = findViewById(R.id.activity_login_loginBtn);
        //signUpTv = findViewById(R.id.activity_login_signUpTv);


        userService = ApiUtils.getUserService();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginEmailEd.getText().toString();
                String password = loginPasswordEd.getText().toString();

               /* if(username.isEmpty()){
                    loginEmailEd.setError("Email is required");
                    loginEmailEd.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
                    loginEmailEd.setError("Enter a valid email");
                    loginEmailEd.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    loginPasswordEd.setError("Password is required");
                    loginPasswordEd.requestFocus();
                    return;
                }
                if(password.length() < 6){
                    loginPasswordEd.setError("Password should be atleast 6 character long");
                    loginPasswordEd.requestFocus();
                    return;
                }*/
                //validate form
                if(validateLogin(username, password)){
                    //do login
                    doLogin(username, password);
                }
            }
        });

    }

    private void doLogin(final String username, final String password) {
        Call<LoginModel> call = userService.login(username,password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {


                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
