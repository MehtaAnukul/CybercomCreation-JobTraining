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
public class UpdateFragment extends Fragment implements View.OnClickListener {

    private EditText updateUserIdEd;
    private EditText updateNameEd;
    private EditText updateEmailEd;
    private Button updateBtn;

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateUserIdEd = view.findViewById(R.id.fragment_update_userIdEd);
        updateNameEd = view.findViewById(R.id.fragment_update_nameEd);
        updateEmailEd = view.findViewById(R.id.fragment_update_emailEd);
        updateBtn = view.findViewById(R.id.fragment_update_updateBtn);

        updateBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_update_updateBtn:
                updateUser();
                break;
        }
    }

    private void updateUser() {
        int id = Integer.parseInt(updateUserIdEd.getText().toString().trim());
        String name = updateNameEd.getText().toString().trim();
        String email = updateEmailEd.getText().toString().trim();

        UserModel userModel = new UserModel();
        userModel.setId(id);
        userModel.setName(name);
        userModel.setEmail(email);

        MainActivity.myAppDatabase.mydataAccessObject().updateUser(userModel);
        Toast.makeText(getContext(), "User Updated....", Toast.LENGTH_SHORT).show();
        updateUserIdEd.setText("");
        updateNameEd.setText("");
        updateEmailEd.setText("");
    }
}
