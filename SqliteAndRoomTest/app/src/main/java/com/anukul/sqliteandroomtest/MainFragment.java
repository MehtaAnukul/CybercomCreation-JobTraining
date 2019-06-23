package com.anukul.sqliteandroomtest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anukul.sqliteandroomtest.R;
import com.anukul.sqliteandroomtest.activity.RoomActivity;
import com.anukul.sqliteandroomtest.activity.SqliteActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    private Button openSqlite;
    private Button openRoom;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        openSqlite = view.findViewById(R.id.fragment_main_openSqliteBtn);
        openRoom = view.findViewById(R.id.fragment_main_openRoomBtn);

        openSqlite.setOnClickListener(this);
        openRoom.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_main_openSqliteBtn:
                Intent sqliteIntent = new Intent(getActivity(),SqliteActivity.class);
                startActivity(sqliteIntent);
                break;
            case R.id.fragment_main_openRoomBtn:
                Intent roomIntent = new Intent(getActivity(),RoomActivity.class);
                startActivity(roomIntent);
                break;
        }
    }
}
