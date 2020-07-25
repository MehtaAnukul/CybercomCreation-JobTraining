package com.anukul.logindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.CaseMap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private EditText emailEd;
    private EditText passwordEd;
    private Button loginBtn;
    private TextView signupTV;
    String email,password;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private FragmentManager fragmentManager;
    CallbackFragment callbackFragment;


    public LoginFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailEd = view.findViewById(R.id.login_fragment_emailEd);
        passwordEd = view.findViewById(R.id.login_fragment_passwordEd);
        loginBtn = view.findViewById(R.id.login_fragment_loginBtn);
        signupTV = view.findViewById(R.id.login_fragment_sigupTv);
        loginBtn.setOnClickListener(this);
        signupTV.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_fragment_loginBtn:
                loginProcess();
                break;
            case R.id.login_fragment_sigupTv:
                if(callbackFragment != null){
                    callbackFragment.changeFragment();
                }
                break;
        }
    }

    private Boolean validateEmail() {
        email = emailEd.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            emailEd.setError("Field cannot be empty");
            return false;
        } else if (!email.matches(emailPattern)) {
            emailEd.setError("Invalid email address");
            return false;
        } else {
            emailEd.setError(null);
            //emailEd.setEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        password = passwordEd.getText().toString().trim();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (password.isEmpty()) {
            passwordEd.setError("Field cannot be empty");
            return false;
        } else if (!password.matches(passwordVal)) {
            passwordEd.setError("Password is too weak");
            return false;
        } else {
            passwordEd.setError(null);
            //passwordEd.setEnabled(false);
            return true;
        }
    }

    private void loginProcess() {

        String userEmail,userPassword;
        userEmail = sharedPreferences.getString("email",null);
        userPassword = sharedPreferences.getString("password",null);

        if( !validateEmail() | !validatePassword() ){
            return;
        }
        else {
            if(email.equals(userEmail) && password.equals(userPassword)){
                Intent intent = new Intent(getActivity(),GridActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                emailEd.setText("");
                passwordEd.setText("");
            }else {
                Toast.makeText(getContext(), "Email & password wrong", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void setCallbackFragment(CallbackFragment callbackFragment) {
        this.callbackFragment = callbackFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {

        sharedPreferences = context.getSharedPreferences("usersData",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }
}