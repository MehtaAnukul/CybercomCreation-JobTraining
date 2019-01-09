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
public class HomeFragment extends Fragment {

    private Button openFirstBtn;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        openFirstBtn = view.findViewById(R.id.fragment_home_openFristBtn);

        openFirstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstFragment firstFragment = new FirstFragment();
                MainActivity.fragmentManager.beginTransaction().replace(R.id.activty_main_framelayout,firstFragment,null)
                        .addToBackStack(null).commit();

            }
        });
        return  view;
    }

}
