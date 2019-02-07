package com.anukul.roomdemo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anukul.roomdemo.R;
import com.anukul.roomdemo.activity.MainActivity;
import com.anukul.roomdemo.model.UserModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment implements View.OnClickListener {

    private EditText userIdEd;
    private EditText userNameEd;
    private EditText userEmailEd;
    private Button saveBtn;

    private long start_time, end_time, total_time;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userIdEd = view.findViewById(R.id.fragment_addUser_userIdEd);
        userNameEd = view.findViewById(R.id.fragment_addUser_nameEd);
        userEmailEd = view.findViewById(R.id.fragment_addUser_emailEd);
        saveBtn = view.findViewById(R.id.fragment_addUser_saveBtn);

        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_addUser_saveBtn:
                insertUserDetails();
                break;
        }
    }

    private void insertUserDetails() {
        int id = Integer.parseInt(userIdEd.getText().toString().trim());
        String name = userNameEd.getText().toString().trim();
        String email = userEmailEd.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            userNameEd.setError("please enter name");
        } else if (TextUtils.isEmpty(email)) {
            userEmailEd.setError("please enter email id");
        } else {

            start_time = System.currentTimeMillis();
            for (int i = 0; i < 500; i++) {
                UserModel userModel = new UserModel();
                userModel.setId(id + i);
                userModel.setName(name + i);
                userModel.setEmail(email + i);
                MainActivity.myAppDatabase.mydataAccessObject().addUser(userModel);
            }
            end_time = System.currentTimeMillis();
            total_time = end_time - start_time;


            Toast.makeText(getContext(), total_time+" Mili Second User added", Toast.LENGTH_SHORT).show();

            userIdEd.setText("");
            userNameEd.setText("");
            userEmailEd.setText("");


        }
    }
}



