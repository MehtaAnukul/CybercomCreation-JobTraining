package com.anukul.sqlitevsroomtest.roomfragment;


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

import com.anukul.sqlitevsroomtest.R;
import com.anukul.sqlitevsroomtest.roomModel.UserModel;
import com.anukul.sqlitevsroomtest.sqlite.RoomActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomAddFragment extends Fragment implements View.OnClickListener {
    private EditText userIdEd;
    private EditText userFNameEd;
    private EditText userLNameEd;
    private EditText userPhoneNoEd;
    private EditText userEmailEd;
    private Button addUserBtn;

    public RoomAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_add, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userIdEd = view.findViewById(R.id.fragment_roomAdd_idEd);
        userFNameEd = view.findViewById(R.id.fragment_roomAdd_firstNameEd);
        userLNameEd = view.findViewById(R.id.fragment_roomAdd_lastNameEd);
        userPhoneNoEd = view.findViewById(R.id.fragment_roomAdd_phoneNoEd);
        userEmailEd = view.findViewById(R.id.fragment_roomAdd_emailEd);

        addUserBtn = view.findViewById(R.id.fragment_roomAdd_addBtn);
        addUserBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_roomAdd_addBtn:
                insertUserDetails();
                break;
        }
    }

    private void insertUserDetails() {
      //  int id = Integer.parseInt(userIdEd.getText().toString().trim());
        String fName = userFNameEd.getText().toString().trim();
        String lName = userLNameEd.getText().toString().trim();
        String phoneNo = userPhoneNoEd.getText().toString().trim();
        String email = userEmailEd.getText().toString().trim();

        if (TextUtils.isEmpty(fName)) {
            userFNameEd.setError("please enter FName");
        } else if (TextUtils.isEmpty(lName)) {
            userLNameEd.setError("please enter LName");
        } else if (TextUtils.isEmpty(phoneNo)) {
            userPhoneNoEd.setError("please enter phoneNo");
        } else if (TextUtils.isEmpty(email)) {
            userEmailEd.setError("please enter email id");
        } else {
            for (int i = 0; i < 30; i++) {
                UserModel userModel = new UserModel();
               // userModel.setId(id + i);
                userModel.setName(fName + i);
                userModel.setLastName(lName + i);
                userModel.setPhoneNo(phoneNo + i);
                userModel.setEmail(email + i);
                RoomActivity.myAppDatabase.mydataAccessObject().addUser(userModel);
            }
            Toast.makeText(getActivity(), "Insert Successfully", Toast.LENGTH_SHORT).show();

            userIdEd.setText("");
            userFNameEd.setText("");
            userLNameEd.setText("");
            userPhoneNoEd.setText("");
            userEmailEd.setText("");
        }
    }
}