package com.anukul.sqlitevsroomtest.sqlite.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anukul.sqlitevsroomtest.R;
import com.anukul.sqlitevsroomtest.sqlite.ContactDbHelper;
import com.anukul.sqlitevsroomtest.sqlite.adapter.ReadContactAdapter;
import com.anukul.sqlitevsroomtest.sqlite.model.ContactModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class SqlitReadContactFragment extends Fragment {

    private RecyclerView customReadContactRecyclerView;
    private static ArrayList<ContactModel> contactModelArrayList;
    private static ReadContactAdapter readContactAdapter;
    private FloatingActionButton floatingActionButton;
    SQLiteDatabase sqLiteDatabase;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public SqlitReadContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sqlit_read_contact, container, false);
        customReadContactRecyclerView = view.findViewById(R.id.fragment_sqliteReadContact_recyclerView);
        floatingActionButton = view.findViewById(R.id.list_sort);

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        contactModelArrayList = contactDbHelper.getAllUser();
        readContactAdapter = new ReadContactAdapter(contactModelArrayList);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(contactModelArrayList, new Comparator<ContactModel>() {
                    @Override
                    public int compare(ContactModel contactModel, ContactModel t1) {
                        String item1 = contactModel.getName();
                        String item2 = t1.getName();
                        return item1.compareTo(item2);
                    }
                });
                readContactAdapter.notifyDataSetChanged();
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        customReadContactRecyclerView.setLayoutManager(layoutManager);
        customReadContactRecyclerView.setAdapter(readContactAdapter);
        if (contactModelArrayList.size() == 0) {

            Toast.makeText(getActivity(), " No Data", Toast.LENGTH_SHORT).show();
        }

        return view;
    }


    public static ReadContactAdapter getReadContactAdapter() {
        return readContactAdapter;
    }

    public static ArrayList<ContactModel> getContactModelArrayList() {
        return contactModelArrayList;
    }

}
