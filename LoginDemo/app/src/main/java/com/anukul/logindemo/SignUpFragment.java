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

    private void SignupProcess() {
        userName = userNameEd.getText().toString().trim();
        email = emailEd.getText().toString().trim();
        phoneNo = phoneNoEd.getText().toString().trim();
        password = passwordEd.getText().toString().trim();

        editor.putString("userName",userName);
        editor.putString("email",email);
        editor.putString("phoneNo",phoneNo);
        editor.putString("password",password);
        editor.apply();

        if(userName.isEmpty() || email.isEmpty() || phoneNo.isEmpty() || password.isEmpty()){
            Toast.makeText(getContext(), "Please enter the Details", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "SignUp Successfully", Toast.LENGTH_SHORT).show();
        }
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