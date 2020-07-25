package com.anukul.logindemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpFragment extends Fragment implements View.OnClickListener{

    private EditText userNameEd;
    private EditText emailEd;
    private EditText phoneNoEd;
    private EditText passwordEd;
    private Button signUpBtn;
    private TextView loginTV;

    String userName,email,password,phoneNo;
    /*String passwordVal = "^" +
            //"(?=.*[0-9])" +         //at least 1 digit
            //"(?=.*[a-z])" +         //at least 1 lower case letter
            //"(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$";*/

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private FragmentManager fragmentManager;
    CallbackFragment callbackFragment;


    public SignUpFragment() {
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        userNameEd = view.findViewById(R.id.signup_fragment_nameEd);
        emailEd = view.findViewById(R.id.signup_fragment_emailEd);
        phoneNoEd = view.findViewById(R.id.signup_fragment_phoneNoEd);
        passwordEd = view.findViewById(R.id.signup_fragment_passwordEd);
        signUpBtn = view.findViewById(R.id.signup_fragment_signupBtn);
        loginTV = view.findViewById(R.id.signup_fragment_loginTv);
        signUpBtn.setOnClickListener(this);
        loginTV.setOnClickListener(this);
        /*loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_fragment_signupBtn:
                SignupProcess();
                break;
            case R.id.signup_fragment_loginTv:
                if(callbackFragment != null){
                    callbackFragment.signUpToLoginFragment();
                }
                break;
        }
    }

    private Boolean validateName(){
        userName = userNameEd.getText().toString().trim();
        if(userName.isEmpty()){
            userNameEd.setError("Field cannot be empty");
            return false;
        }else {
            userNameEd.setError(null);
            userNameEd.setEnabled(false);
            return true;
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
            emailEd.setEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo() {
        phoneNo = phoneNoEd.getText().toString().trim();

        if (phoneNo.isEmpty()) {
            phoneNoEd.setError("Field cannot be empty");
            return false;
        } else {
            phoneNoEd.setError(null);
            phoneNoEd.setEnabled(false);
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
            passwordEd.setEnabled(false);
            return true;
        }
    }


    private void SignupProcess() {
        /*userName = userNameEd.getText().toString().trim();
        email = emailEd.getText().toString().trim();
        phoneNo = phoneNoEd.getText().toString().trim();
        password = passwordEd.getText().toString().trim();*/

        if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail()){
            return;
        }else {
            Toast.makeText(getContext(), "SignUp Successfully", Toast.LENGTH_SHORT).show();
        }

        editor.putString("userName",userName);
        editor.putString("email",email);
        editor.putString("phoneNo",phoneNo);
        editor.putString("password",password);
        editor.apply();

        /*if(userName.isEmpty() || email.isEmpty() || phoneNo.isEmpty() || password.isEmpty()){
            Toast.makeText(getContext(), "Please enter the Details", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "SignUp Successfully", Toast.LENGTH_SHORT).show();
        }*/
        userNameEd.setText("");
        emailEd.setText("");
        phoneNoEd.setText("");
        passwordEd.setText("");
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