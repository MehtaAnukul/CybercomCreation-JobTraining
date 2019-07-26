package com.anukul.sqlitelogindemo.fragment;


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

import com.anukul.sqlitelogindemo.R;
import com.anukul.sqlitelogindemo.listener.OnDbOprationListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    OnDbOprationListener onDbOprationListener;

    private Button viewBtn;
    private Button deleteBtn;
    private Button updateBtn;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewBtn = view.findViewById(R.id.fragment_home_viewContactBtn);
        updateBtn = view.findViewById(R.id.fragment_home_updateContactBtn);
        deleteBtn = view.findViewById(R.id.fragment_home_deleteContactBtn);

        viewBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_home_viewContactBtn:
                onDbOprationListener.dbOpPerformed(0);
                break;
            case R.id.fragment_home_updateContactBtn:
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
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement the interface method..");
        }

    }
}
