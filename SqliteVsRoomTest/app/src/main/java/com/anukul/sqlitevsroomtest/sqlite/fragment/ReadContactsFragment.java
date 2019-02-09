package com.anukul.sqlitevsroomtest.sqlite.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.anukul.sqlitevsroomtest.R;
import com.anukul.sqlitevsroomtest.sqlite.ContactDbHelper;
import com.anukul.sqlitevsroomtest.sqlite.adapter.ReadContactAdapter;
import com.anukul.sqlitevsroomtest.sqlite.model.ContactModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadContactsFragment extends Fragment {

    private RecyclerView customReadContactRecyclerView;
    private ArrayList<ContactModel> contactModelArrayList;
    private ReadContactAdapter readContactAdapter;



    //private TextView helloTv;

    SQLiteDatabase sqLiteDatabase;


    public ReadContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_contacts, container, false);
        //helloTv = view.findViewById(R.id.fragment_read_contacts_diplayTv);
//        readContact();

            customReadContactRecyclerView = view.findViewById(R.id.fragment_readContact_recyclerView);


            ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
            contactModelArrayList = contactDbHelper.getAllUser();
            readContactAdapter = new ReadContactAdapter(contactModelArrayList);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            customReadContactRecyclerView.setLayoutManager(layoutManager);
            customReadContactRecyclerView.setAdapter(readContactAdapter);


            Toast.makeText(getActivity(), " Success", Toast.LENGTH_SHORT).show();
            // readContactData();
            return view;
        }


    }





