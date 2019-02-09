package com.anukul.sqliteandroomtest;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class SqliteAddFragment extends Fragment implements View.OnClickListener {
    private EditText contactIdEd;
    private EditText fNameEd;
    private EditText lNameEd;
    private EditText phoneNoEd;
    private EditText emailEd;
    private Button addBtn;

    SQLiteDatabase sqLiteDatabase;

    public SqliteAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sqlite_add, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contactIdEd = view.findViewById(R.id.fragment_sqlite_idEd);
        fNameEd = view.findViewById(R.id.fragment_sqlite_firstNameEd);
        lNameEd = view.findViewById(R.id.fragment_sqlite_lastNameEd);
        phoneNoEd = view.findViewById(R.id.fragment_sqlite_phoneNumEd);
        emailEd = view.findViewById(R.id.fragment_sqlite_emailEd);
        addBtn = view.findViewById(R.id.fragment_sqlite_addBtn);

        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_sqlite_addBtn:
                sqliteInsertContact();
                break;
        }
    }

    private void sqliteInsertContact() {
        String fName = fNameEd.getText().toString().trim();
        String lName = lNameEd.getText().toString().trim();
        String phoneNo = phoneNoEd.getText().toString().trim();
        String email = emailEd.getText().toString().trim();

        if (TextUtils.isEmpty(fName)) {
            fNameEd.setError("Please Enter FName");
        } else if (TextUtils.isEmpty(lName)) {
            lNameEd.setError("Please Enter LName");
        } else if (TextUtils.isEmpty(phoneNo)) {
            phoneNoEd.setError("Please Enter PhoneNo");
        } else if (TextUtils.isEmpty(email)) {
            emailEd.setError("Please Enter Email");
        } else {
            ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
            sqLiteDatabase = contactDbHelper.getWritableDatabase();

            for (int i = 0; i < 30; i++) {
                contactDbHelper.insertContact(new ContactModel(fName, lName, phoneNo, email), sqLiteDatabase);
            }
            contactDbHelper.close();
            fNameEd.setText("");
            lNameEd.setText("");
            phoneNoEd.setText("");
            emailEd.setText("");
            Toast.makeText(getActivity(), "Insert Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}

