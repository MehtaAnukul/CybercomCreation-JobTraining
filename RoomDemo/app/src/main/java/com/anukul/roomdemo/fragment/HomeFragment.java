package com.anukul.roomdemo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anukul.roomdemo.activity.MainActivity;
import com.anukul.roomdemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private Button addUserBtn;
    private Button viewUserBtn;
    private Button updateUserBtn;
    private Button deleteUserBtn;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getFragmentManager();
        addUserBtn = view.findViewById(R.id.fragment_home_addUserBtn);
        viewUserBtn = view.findViewById(R.id.fragment_home_viewUserBtn);
        updateUserBtn = view.findViewById(R.id.fragment_home_updateUserBtn);
        deleteUserBtn = view.findViewById(R.id.fragment_home_deleteUserBtn);

        addUserBtn.setOnClickListener(this);
        viewUserBtn.setOnClickListener(this);
        updateUserBtn.setOnClickListener(this);
        deleteUserBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_home_addUserBtn:
                addUser();
                break;
            case R.id.fragment_home_viewUserBtn:
                viewUser();
                break;
            case R.id.fragment_home_updateUserBtn:
                updateUser();
                break;
            case R.id.fragment_home_deleteUserBtn:
                deleteUser();
                break;
        }
    }

    private void deleteUser() {
        DeleteUserFragment deleteUserFragment = new DeleteUserFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activtiy_main_framelayout_fragmentContainer,deleteUserFragment,MainActivity.class.getSimpleName());
        fragmentTransaction.addToBackStack(MainActivity.class.getSimpleName());
        fragmentTransaction.commit();
    }

    private void updateUser() {
        UpdateFragment updateFragment = new UpdateFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activtiy_main_framelayout_fragmentContainer,updateFragment,MainActivity.class.getSimpleName());
        fragmentTransaction.addToBackStack(MainActivity.class.getSimpleName());
        fragmentTransaction.commit();
    }

    private void viewUser()  {
        ReadUserFragment readUserFragment = new ReadUserFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activtiy_main_framelayout_fragmentContainer,readUserFragment,MainActivity.class.getSimpleName());
        fragmentTransaction.addToBackStack(MainActivity.class.getSimpleName());
        fragmentTransaction.commit();
    }

    private void addUser()  {
        AddUserFragment addUserFragment = new AddUserFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activtiy_main_framelayout_fragmentContainer,addUserFragment,MainActivity.class.getSimpleName());
        fragmentTransaction.addToBackStack(MainActivity.class.getSimpleName());
        fragmentTransaction.commit();
    }
}
