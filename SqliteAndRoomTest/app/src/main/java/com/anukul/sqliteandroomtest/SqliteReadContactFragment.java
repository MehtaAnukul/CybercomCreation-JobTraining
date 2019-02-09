package com.anukul.sqliteandroomtest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SqliteReadContactFragment extends Fragment {

    private RecyclerView sqliteCustomReadContactRecyclerView;
    private ArrayList<ContactModel> contactModelArrayList;
    private SqliteReadContactAdapter sqliteReadContactAdapter;

    public SqliteReadContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sqlite_read_contact, container, false);

        sqliteCustomReadContactRecyclerView = view.findViewById(R.id.fragment_sqliteReadContact_recyclerView);
        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        contactModelArrayList = contactDbHelper.getAllUser();
        sqliteReadContactAdapter = new SqliteReadContactAdapter(contactModelArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        sqliteCustomReadContactRecyclerView.setLayoutManager(layoutManager);
        sqliteCustomReadContactRecyclerView.setAdapter(sqliteReadContactAdapter);
        Toast.makeText(getActivity(), "View Success", Toast.LENGTH_SHORT).show();
        return view;
    }

}
