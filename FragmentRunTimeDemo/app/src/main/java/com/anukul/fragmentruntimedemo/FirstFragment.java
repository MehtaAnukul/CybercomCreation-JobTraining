package com.anukul.fragmentruntimedemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private Button openSecondBtn;


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        openSecondBtn = view.findViewById(R.id.fragment_first_secondBtn);

        openSecondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondFragment secondFragment = new SecondFragment();
                MainActivity.fragmentManager.beginTransaction().replace(R.id.activty_main_framelayout,secondFragment,null).addToBackStack(null).commit();
            }
        });
        return view;
    }

}
