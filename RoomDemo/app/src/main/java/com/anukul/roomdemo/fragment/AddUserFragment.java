package com.anukul.roomdemo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anukul.roomdemo.activity.MainActivity;
import com.anukul.roomdemo.R;
import com.anukul.roomdemo.model.UserModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment implements View.OnClickListener {

    private EditText userIdEd;
    private EditText userNameEd;
    private EditText userEmailEd;
    private Button saveBtn;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_user, container, false);
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
        switch (v.getId()){
            case R.id.fragment_addUser_saveBtn:
                insertUserDetails();
                break;
        }
    }

    private void insertUserDetails() {
        int id = Integer.parseInt(userIdEd.getText().toString().trim());
        String name = userNameEd.getText().toString().trim();
        String email = userEmailEd.getText().toString().trim();

        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setName(name);
        userModel.setEmail(email);

        MainActivity.myAppDatabase.mydataAccessObject().addUser(userModel);
        Toast.makeText(getContext(), "User added Successfully", Toast.LENGTH_SHORT).show();

        userIdEd.setText("");
        userNameEd.setText("");
        userEmailEd.setText("");



    }
}
