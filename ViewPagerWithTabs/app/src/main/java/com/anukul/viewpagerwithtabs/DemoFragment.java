package com.anukul.viewpagerwithtabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {

    private TextView fragmentTv;


    public DemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        fragmentTv = view.findViewById(R.id.fragment_demo_fragmentTv);

        String message = getArguments().getString(AppConstant.KEY_FRAGMENT);
        fragmentTv.setText(message);
        return view;
    }

}
