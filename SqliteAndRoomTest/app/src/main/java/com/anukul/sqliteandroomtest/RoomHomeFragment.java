package com.anukul.sqliteandroomtest;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomHomeFragment extends Fragment implements View.OnClickListener{

    private FragmentManager fragmentManager;

    private Button addUserHomeBtn;
    private Button viewUserHomeBtn;



    public RoomHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getFragmentManager();

        addUserHomeBtn = view.findViewById(R.id.fragment_roomHome_addUser);
        viewUserHomeBtn = view.findViewById(R.id.fragment_roomHome_viewUser);

        addUserHomeBtn.setOnClickListener(this);
        viewUserHomeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_roomHome_addUser:
                addUser();
                break;
            case R.id.fragment_roomHome_viewUser:
                viewUser();
                break;
        }
    }

    private void viewUser() {
        RoomReadUserFragment roomReadUserFragment = new RoomReadUserFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_room_fragment_contanier,roomReadUserFragment,RoomReadUserFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(RoomReadUserFragment.class.getSimpleName());
        fragmentTransaction.hide(RoomHomeFragment.this);
        fragmentTransaction.commit();
    }

    private void addUser() {
        RoomAddFragment roomAddFragment = new RoomAddFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_room_fragment_contanier,roomAddFragment,RoomAddFragment.class.getSimpleName());
        fragmentTransaction.addToBackStack(RoomAddFragment.class.getSimpleName());
        fragmentTransaction.hide(RoomHomeFragment.this);
        fragmentTransaction.commit();
    }
}
