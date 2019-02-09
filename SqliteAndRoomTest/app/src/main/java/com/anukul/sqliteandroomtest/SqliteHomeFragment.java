package com.anukul.sqliteandroomtest;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anukul.sqliteandroomtest.listener.OnDbOprationListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class SqliteHomeFragment extends Fragment implements View.OnClickListener{
    OnDbOprationListener onDbOprationListener;
    private Button sqliteAddBtn;
    private Button sqliteViewBtn;



    public SqliteHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sqlite_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sqliteAddBtn = view.findViewById(R.id.fragment_sqliteHome_addContactBtn);
        sqliteViewBtn = view.findViewById(R.id.fragment_sqliteHome_viewContactBtn);

        sqliteAddBtn.setOnClickListener(this);
        sqliteViewBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_sqliteHome_addContactBtn:
                onDbOprationListener.dbOpPerformed(0);
                break;
            case R.id.fragment_sqliteHome_viewContactBtn:
                onDbOprationListener.dbOpPerformed(1);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try {
            onDbOprationListener = (OnDbOprationListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement the interface method..");
        }
    }
}
