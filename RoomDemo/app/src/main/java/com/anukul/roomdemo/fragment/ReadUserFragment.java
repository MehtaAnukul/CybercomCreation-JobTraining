package com.anukul.roomdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anukul.roomdemo.R;
import com.anukul.roomdemo.ReadUserAdapter;
import com.anukul.roomdemo.activity.MainActivity;
import com.anukul.roomdemo.model.UserModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {
    // private TextView displayTv;
    private RecyclerView customReadUserRecyclerView;
    private ArrayList<UserModel> userModelArrayList;
    private ReadUserAdapter readUserAdapter;

    private long start_time, end_time, total_time;

    public ReadUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_user, container, false);
        //displayTv = view.findViewById(R.id.fragment_readUser_displayTv);

        customReadUserRecyclerView = view.findViewById(R.id.fragment_readUser_recyclerView);

        start_time = System.currentTimeMillis();
        userModelArrayList = (ArrayList<UserModel>) MainActivity.myAppDatabase.mydataAccessObject().getUsers();
       // String info = "";

        readUserAdapter = new ReadUserAdapter(userModelArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        customReadUserRecyclerView.setLayoutManager(layoutManager);
        customReadUserRecyclerView.setAdapter(readUserAdapter);
        end_time = System.currentTimeMillis();
        total_time = end_time - start_time;
            Toast.makeText(getActivity(), total_time+" mili seconds", Toast.LENGTH_SHORT).show();
//
//        for (UserModel userModel : userModels) {
//            int id = userModel.getId();
//            String name = userModel.getName();
//            String email = userModel.getEmail();
//
//            info = info + "\n\n" +
//                    "Id :" + id + "\n"
//                    "Name :" + name + "\n" + +
//                    "Email :" + email;
//        }
        //displayTv.setText(info);

        return view;
    }

}
