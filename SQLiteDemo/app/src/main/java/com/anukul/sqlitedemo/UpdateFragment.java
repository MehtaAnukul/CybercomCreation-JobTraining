package com.anukul.sqlitedemo;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment implements View.OnClickListener {
    private EditText updateContactId ;
    private EditText updateNameEd;
    private EditText updateEmailEd;
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
        updateEmailEd = view.findViewById(R.id.fragment_update_emailEd);
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
        String email = updateEmailEd.getText().toString().trim();

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        sqLiteDatabase = contactDbHelper.getWritableDatabase();

        contactDbHelper.updateContacts(new ContactModel(id,name,email),id);
        contactDbHelper.close();

        Toast.makeText(getActivity(), "Contact Updated", Toast.LENGTH_SHORT).show();
        updateContactId.setText("");
        updateNameEd.setText("");
        updateEmailEd.setText("");
    }
}
