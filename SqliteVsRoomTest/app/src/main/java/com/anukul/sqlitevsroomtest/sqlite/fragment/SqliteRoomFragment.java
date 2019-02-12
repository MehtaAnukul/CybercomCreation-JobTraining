package com.anukul.sqlitevsroomtest.sqlite.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anukul.sqlitevsroomtest.R;
import com.anukul.sqlitevsroomtest.sqlite.RoomActivity;
import com.anukul.sqlitevsroomtest.sqlite.activity.SqliteActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SqliteRoomFragment extends Fragment implements View.OnClickListener {


    private Button openSqliteBtn;
    private Button openRoomBtn;


    public SqliteRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sqlite_room, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openSqliteBtn = view.findViewById(R.id.fragment_sqliteRoom_openSqliteBtn);
        openRoomBtn = view.findViewById(R.id.fragment_sqliteRoom_openroomBtn);

        openSqliteBtn.setOnClickListener(this);
        openRoomBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_sqliteRoom_openSqliteBtn:
                Intent sqliteIntent = new Intent(getActivity(),SqliteActivity.class);
                startActivity(sqliteIntent);
                break;
            case R.id.fragment_sqliteRoom_openroomBtn:
                Intent roomIntent = new Intent(getActivity(),RoomActivity.class);
                startActivity(roomIntent);
                break;
        }
    }
}
