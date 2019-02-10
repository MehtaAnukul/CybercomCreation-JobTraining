package com.anukul.sqlitevsroomtest.roomfragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anukul.sqlitevsroomtest.R;
import com.anukul.sqlitevsroomtest.roomAdapter.RoomReadUserAdapter;
import com.anukul.sqlitevsroomtest.roomModel.UserModel;
import com.anukul.sqlitevsroomtest.sqlite.RoomActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomReadUserFragment extends Fragment {
    private RecyclerView customReadUserRecyclerView;
    private ArrayList<UserModel> userModelArrayList;

    private RoomReadUserAdapter roomReadUserAdapter;

    private FloatingActionButton floatingActionButton;

    public RoomReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_read_user, container, false);

        floatingActionButton = view.findViewById(R.id.roomList_sort);

        customReadUserRecyclerView = view.findViewById(R.id.fragment_roomReadUser_recyclerView);

        userModelArrayList = (ArrayList<UserModel>) RoomActivity.myAppDatabase.mydataAccessObject().getUsers();

        roomReadUserAdapter  = new RoomReadUserAdapter(userModelArrayList);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(userModelArrayList, new Comparator<UserModel>() {
                    @Override
                    public int compare(UserModel userModel, UserModel t1) {
                        String item1 = userModel.getName();
                        String item2 = t1.getName();
                        return item1.compareTo(item2);
                    }
                });
                roomReadUserAdapter.notifyDataSetChanged();
            }
        });



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        customReadUserRecyclerView.setLayoutManager(layoutManager);
        customReadUserRecyclerView.setAdapter(roomReadUserAdapter);

        Toast.makeText(getActivity(), "View Success", Toast.LENGTH_SHORT).show();
        return view;
    }

}
