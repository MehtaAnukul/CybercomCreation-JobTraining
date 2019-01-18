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
public class DeleteUserFragment extends Fragment implements View.OnClickListener {
    private EditText userIDEd;
    private Button deleteUserBtn;

    public DeleteUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delete_user, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userIDEd = view.findViewById(R.id.fragment_deleteUser_userIdEd);
        deleteUserBtn = view.findViewById(R.id.fragment_deleteUser_deleteBtn);

        deleteUserBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_deleteUser_deleteBtn:
                deleteUser();
                break;
        }

    }

    private void deleteUser() {
        int id = Integer.parseInt(userIDEd.getText().toString().trim());

        UserModel userModel = new UserModel();
        userModel.setId(id);

        MainActivity.myAppDatabase.mydataAccessObject().deleteUser(userModel);
        Toast.makeText(getContext(), "User Successfully removed", Toast.LENGTH_SHORT).show();
        userIDEd.setText("");
    }
}
