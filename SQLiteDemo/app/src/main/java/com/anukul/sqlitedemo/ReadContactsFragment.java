package com.anukul.sqlitedemo;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadContactsFragment extends Fragment {

    private TextView helloTv;

    SQLiteDatabase sqLiteDatabase;


    public ReadContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_read_contacts, container, false);
        helloTv = view.findViewById(R.id.fragment_read_contacts_diplayTv);
        readContact();
        return view;
    }

    private void readContact() {
        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        sqLiteDatabase = contactDbHelper.getWritableDatabase();

        contactDbHelper.getAllUser();


        ContactModel contactModel = new ContactModel();
        String info = "";
        int id = contactModel.getId();
        String name = contactModel.getName();
        String email = contactModel.getEmail();

        info = info+"\n\n"+id+"\nName : "+name+"\nEmail : "+email;
        helloTv.setText(info);
        contactDbHelper.close();
    }


}
