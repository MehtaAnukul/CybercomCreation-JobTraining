package com.anukul.logindemo;

import android.content.Context;
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
    String email,pasword;

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

    private void loginProcess() {
        email = emailEd.getText().toString().trim();
        pasword = passwordEd.getText().toString().trim();


        String userEmail,userPassword;
        userEmail = sharedPreferences.getString("email",null);
        userPassword = sharedPreferences.getString("password",null);



        if(email.isEmpty() || pasword.isEmpty()){
            Toast.makeText(getContext(), "Please enter the Details", Toast.LENGTH_SHORT).show();
        }else {
            if(email.equals(userEmail) && pasword.equals(userPassword)){
                Toast.makeText(getContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "something want wrong", Toast.LENGTH_SHORT).show();
            }
        }
        emailEd.setText("");
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