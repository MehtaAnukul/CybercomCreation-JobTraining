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
import android.widget.LinearLayout;
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
public class SqlitReadContactFragment extends Fragment implements View.OnClickListener {

    private RecyclerView customReadContactRecyclerView;
    private static ArrayList<ContactModel> contactModelArrayList;
    private static ReadContactAdapter readContactAdapter;

    private FloatingActionButton floatingActionButton;
    private FloatingActionButton fFloatingActionButton;
    private FloatingActionButton lFloatingActionButton;
    private FloatingActionButton emailfloatingActionButton;
    private boolean fabExpanded = false;

    private LinearLayout firstNameFabLayout;
    private LinearLayout lastNameFabLayout;
    private LinearLayout emailFabLayout;

    //SQLiteDatabase sqLiteDatabase;

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
        fFloatingActionButton = view.findViewById(R.id.firstName_floatingBtn);
        lFloatingActionButton = view.findViewById(R.id.lastName_floatingBtn);
        emailfloatingActionButton = view.findViewById(R.id.email_floatingBtn);

        firstNameFabLayout = view.findViewById(R.id.fab_firstName_layout);
        lastNameFabLayout = view.findViewById(R.id.fab_lastname_layout);
        emailFabLayout = view.findViewById(R.id.fab_email_layout);

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        contactModelArrayList = contactDbHelper.getAllUser();
        readContactAdapter = new ReadContactAdapter(contactModelArrayList);

        firstNameFabLayout.setVisibility(View.INVISIBLE);
        lastNameFabLayout.setVisibility(View.INVISIBLE);
        emailFabLayout.setVisibility(View.INVISIBLE);

        floatingActionButton.setOnClickListener(this);
        fFloatingActionButton.setOnClickListener(this);
        lFloatingActionButton.setOnClickListener(this);
        emailfloatingActionButton.setOnClickListener(this);

       /* floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fabExpanded == true){
                    closeSubMenusFab();
                }else {
                    openSubMenusFab();
                }
                //closeSubMenusFab();

            }
        });*/


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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.list_sort:
                if (fabExpanded == true) {
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
                break;
            case R.id.firstName_floatingBtn:
                Collections.sort(contactModelArrayList, new Comparator<ContactModel>() {
                    @Override
                    public int compare(ContactModel contactModel, ContactModel t1) {
                        String item1 = contactModel.getName();
                        String item2 = t1.getName();
                        return item1.compareTo(item2);
                    }
                });
                readContactAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "FirstName through Sorted", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lastName_floatingBtn:
                Collections.sort(contactModelArrayList, new Comparator<ContactModel>() {
                    @Override
                    public int compare(ContactModel contactModel, ContactModel t1) {
                        String item1 = contactModel.getLastName();
                        String item2 = t1.getLastName();
                        return item1.compareTo(item2);
                    }
                });
                readContactAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "LastName through Sorted", Toast.LENGTH_SHORT).show();
                break;
            case R.id.email_floatingBtn:
                Collections.sort(contactModelArrayList, new Comparator<ContactModel>() {
                    @Override
                    public int compare(ContactModel contactModel, ContactModel t1) {
                        String item1 = contactModel.getEmail();
                        String iten2 = t1.getEmail();
                        return item1.compareTo(iten2);
                    }
                });
                readContactAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Email through Sorted", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void openSubMenusFab() {
        firstNameFabLayout.setVisibility(View.VISIBLE);
        lastNameFabLayout.setVisibility(View.VISIBLE);
        emailFabLayout.setVisibility(View.VISIBLE);
        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
        fabExpanded = true;

    }

    private void closeSubMenusFab() {
        firstNameFabLayout.setVisibility(View.INVISIBLE);
        lastNameFabLayout.setVisibility(View.INVISIBLE);
        emailFabLayout.setVisibility(View.INVISIBLE);
        floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_sort_black_24dp));
        fabExpanded = false;
        /*Collections.sort(contactModelArrayList, new Comparator<ContactModel>() {
            @Override
            public int compare(ContactModel contactModel, ContactModel t1) {
                String item1 = contactModel.getName();
                String item2 = t1.getName();
                return item1.compareTo(item2);
            }
        });
        readContactAdapter.notifyDataSetChanged();*/
    }

}
