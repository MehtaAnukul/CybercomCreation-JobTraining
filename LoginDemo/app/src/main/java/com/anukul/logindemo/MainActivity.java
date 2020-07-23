package com.anukul.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements CallbackFragment{

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment();



    }

    private void addFragment() {
        SignUpFragment signUpFragment = new SignUpFragment();
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.hide(signUpFragment);
        fragmentTransaction.add(R.id.activity_main_containerFramLayout,loginFragment);

        fragmentTransaction.commit();
    }

    private void replaceFragment() {
        //fragment = new SignUpFragment();
        LoginFragment loginFragment = new LoginFragment();
        SignUpFragment signUpFragment = new SignUpFragment();
        signUpFragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.activity_main_containerFramLayout,signUpFragment);
        fragmentTransaction.hide(loginFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void changeFragment() {
        replaceFragment();
    }

    @Override
    public void signUpToLoginFragment() {
        addFragment();
    }


}