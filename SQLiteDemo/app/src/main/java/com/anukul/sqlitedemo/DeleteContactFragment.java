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
public class DeleteContactFragment extends Fragment implements View.OnClickListener {

    private EditText deleteContactId;
    private Button deleteBtn;

    SQLiteDatabase sqLiteDatabase;


    public DeleteContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delete_contact, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        deleteContactId = view.findViewById(R.id.fragment_deleteContact_contactIdEd);
        deleteBtn = view.findViewById(R.id.fragment_deleteContact_deleteBtn);

        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_deleteContact_deleteBtn:
                deleteCOntact();
                break;

        }
    }

    private void deleteCOntact() {
        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        sqLiteDatabase = contactDbHelper.getWritableDatabase();

        int id = Integer.parseInt(deleteContactId.getText().toString().trim());

        contactDbHelper.deleteContacts(id,sqLiteDatabase);
        contactDbHelper.close();
        Toast.makeText(getActivity(), "Contact Removed Seccessfully", Toast.LENGTH_SHORT).show();
        deleteContactId.setText("");

    }
}
