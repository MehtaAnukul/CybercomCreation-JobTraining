package com.anukul.sqlitelogindemo.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anukul.sqlitelogindemo.ContactDbHelper;
import com.anukul.sqlitelogindemo.ContactModel;
import com.anukul.sqlitelogindemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment implements View.OnClickListener {
    private EditText updateContactId ;
    private EditText updateNameEd;
    private EditText updateLastNameEd;
    private EditText updateEmailEd;
    private EditText updatePhoneNoEd;
    private Button updateBtn;

    SQLiteDatabase sqLiteDatabase;


    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateContactId  = view.findViewById(R.id.fragment_update_contactEd);
        updateNameEd = view.findViewById(R.id.fragment_update_nameEd);
        updateLastNameEd = view.findViewById(R.id.fragment_update_lastNameEd);
        updateEmailEd = view.findViewById(R.id.fragment_update_emailEd);
        updatePhoneNoEd = view.findViewById(R.id.fragment_update_phoneNoEd);
        updateBtn = view.findViewById(R.id.fragment_update_updateBtn);

        updateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_update_updateBtn:
                updateContact();
                break;
        }
    }

    private void updateContact() {
        int id = Integer.parseInt(updateContactId.getText().toString().trim());
        String name = updateNameEd.getText().toString().trim();
        String lastName = updateLastNameEd.getText().toString().trim();
        String email = updateEmailEd.getText().toString().trim();
        String phoneNo = updatePhoneNoEd.getText().toString().trim();

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        sqLiteDatabase = contactDbHelper.getWritableDatabase();

        contactDbHelper.updateContacts(new ContactModel(id,name,lastName,phoneNo,email),id);
        contactDbHelper.close();

        Toast.makeText(getActivity(), "Contact Updated", Toast.LENGTH_SHORT).show();
        updateContactId.setText("");
        updateNameEd.setText("");
        updateLastNameEd.setText("");
        updateEmailEd.setText("");
        updatePhoneNoEd.setText("");
    }
}
