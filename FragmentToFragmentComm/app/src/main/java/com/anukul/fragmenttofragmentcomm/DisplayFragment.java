package com.anukul.fragmenttofragmentcomm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {

    private TextView nameTv;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_display, container, false);

        nameTv = view.findViewById(R.id.fragment_display_nameTv);

        Bundle bundle = getArguments();
        final String name = bundle.getString(AppConstent.KEY_NAME);
        nameTv.setText(name);

        return view;
    }



}
