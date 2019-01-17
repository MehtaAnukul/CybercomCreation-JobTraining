package com.anukul.roomdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {
    private TextView displayTv;

    public ReadUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_user, container, false);
        displayTv = view.findViewById(R.id.fragment_readUser_displayTv);

        List<UserModel> userModels = MainActivity.myAppDatabase.mydataAccessObject().getUsers();
        String info = "";

        for (UserModel userModel : userModels) {
            int id = userModel.getId();
            String name = userModel.getName();
            String email = userModel.getEmail();

            info = info + "\n\n" +
                    "Id :" + id + "\n" +
                    "Name :" + name + "\n" +
                    "Email :" + email;
        }
        displayTv.setText(info);

        return view;
    }

}
