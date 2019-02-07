package com.anukul.sqlitedemo.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anukul.sqlitedemo.ContactDbHelper;
import com.anukul.sqlitedemo.R;
import com.anukul.sqlitedemo.ReadContactAdapter;
import com.anukul.sqlitedemo.model.ContactModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadContactsFragment extends Fragment {
    private RecyclerView customReadContactRecyclerView;
    private ArrayList<ContactModel> contactModelArrayList;
    private ReadContactAdapter readContactAdapter;

    private long start_time, end_time, total_time;

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

        start_time = System.currentTimeMillis();
        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        contactModelArrayList = contactDbHelper.getAllUser();
        readContactAdapter = new ReadContactAdapter(contactModelArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        customReadContactRecyclerView.setLayoutManager(layoutManager);
        customReadContactRecyclerView.setAdapter(readContactAdapter);
        end_time = System.currentTimeMillis();
        total_time = end_time - start_time;
        Toast.makeText(getActivity(), total_time+" mili seconds", Toast.LENGTH_SHORT).show();
        // readContactData();
        return view;
    }

   /* private void readContactData() {
        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());

        ArrayList<ContactModel> contactModelArrayList = contactDbHelper.getAllUser();
        String info = "";

//        for (ContactModel contactModel : contactModelArrayList) {
//        }
        for (int i = 0; i < contactModelArrayList.size(); i++) {

            info = info + "\n\n" + contactModelArrayList.get(i).getId() +
                    "\nName : " + contactModelArrayList.get(i).getName() +
                    "\nEmail : " + contactModelArrayList.get(i).getEmail();
            //  helloTv.setText(info);

        }
    }*/

    /*private void readContact() {
        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        sqLiteDatabase = contactDbHelper.getReadableDatabase();

        Cursor cursor = contactDbHelper.readContact(sqLiteDatabase);

        //ContactModel contactModel = new ContactModel();
        String info = "";

        while (cursor.moveToNext()) {
            String id = Integer.toString(cursor.getInt(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_ID)));
            String name = cursor.getString(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_NAME));
            String email = cursor.getString(cursor.getColumnIndex(ContactDbConstant.CONTACT_COLUMN_EMAIL));

            info = info + "\n\n" + id +
                    "\nName : " + name +
                    "\nEmail : " + email;

        }
        helloTv.setText(info);
        contactDbHelper.close();*/
    //}


}
