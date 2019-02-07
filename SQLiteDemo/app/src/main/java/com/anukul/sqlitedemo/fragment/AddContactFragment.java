package com.anukul.sqlitedemo.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anukul.sqlitedemo.ContactDbHelper;
import com.anukul.sqlitedemo.R;
import com.anukul.sqlitedemo.model.ContactModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddContactFragment extends Fragment implements View.OnClickListener {

    private EditText contactIdEd;
    private EditText nameEd;
    private EditText emailEd;
    private Button saveBtn;

    private long start_time,end_time,total_time;

    SQLiteDatabase sqLiteDatabase;

    public AddContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactIdEd = view.findViewById(R.id.fragment_addContact_contactIdEd);
        nameEd = view.findViewById(R.id.fragment_addContact_nameEd);
        emailEd = view.findViewById(R.id.fragment_addContact_emailEd);
        saveBtn = view.findViewById(R.id.fragment_addContact_saveBtn);

        saveBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_addContact_saveBtn:
                insertContact();
                break;
        }
    }

    private void insertContact() {
//        int conteactId = Integer.parseInt(contactIdEd.getText().toString().trim());

        String name = nameEd.getText().toString().trim();
        String email = emailEd.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            nameEd.setError("Please enter name");
        } else if (TextUtils.isEmpty(email)) {
            emailEd.setError("please enter email");
        } else {


            // ContactModel contactModel = new ContactModel();
            ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
            sqLiteDatabase = contactDbHelper.getWritableDatabase();
            start_time = System.currentTimeMillis();
            for (int i = 0; i < 5000; i++) {

                contactDbHelper.insertContact(new ContactModel(name + i, email + i), sqLiteDatabase);
            }
            end_time = System.currentTimeMillis();
            total_time = end_time - start_time;
            contactDbHelper.close();
            contactIdEd.setText("");
            nameEd.setText("");
            emailEd.setText("");
            Toast.makeText(getActivity(), total_time + "Mili Second taken by the record User are Added", Toast.LENGTH_LONG).show();
            // Toast.makeText(getActivity(), "Contact saved sucessfully", Toast.LENGTH_SHORT).show();

        }
    }
}
