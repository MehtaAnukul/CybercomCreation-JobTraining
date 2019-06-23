package com.anukul.sqliteandroomtest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anukul.sqliteandroomtest.R;
import com.anukul.sqliteandroomtest.activity.RoomActivity;
import com.anukul.sqliteandroomtest.roomAdapter.RoomReadUserAdapter;
import com.anukul.sqliteandroomtest.roomModel.UserModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomReadUserFragment extends Fragment {
    private RecyclerView customReadUserRecyclerView;
    private ArrayList<UserModel> userModelArrayList;

    private RoomReadUserAdapter roomReadUserAdapter;

    public RoomReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_read_user, container, false);

        customReadUserRecyclerView = view.findViewById(R.id.fragment_roomReadUser_recyclerView);

        userModelArrayList = (ArrayList<UserModel>) RoomActivity.myAppDatabase.mydataAccessObject().getUsers();

        roomReadUserAdapter  = new RoomReadUserAdapter(userModelArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        customReadUserRecyclerView.setLayoutManager(layoutManager);
        customReadUserRecyclerView.setAdapter(roomReadUserAdapter);

        Toast.makeText(getActivity(), "View Success", Toast.LENGTH_SHORT).show();
        return view;
    }

}
