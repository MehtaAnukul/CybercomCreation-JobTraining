package com.anukul.fragmenttofragmentcomm;


import android.nfc.tech.NfcA;
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
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    private EditText nameEd;
    private Button sendBtn;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameEd = view.findViewById(R.id.fragment_message_nameEd);
        sendBtn = view.findViewById(R.id.fragment_message_sendBtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameEd.getText().toString().trim();
                Bundle bundle = new Bundle();
                bundle.putString(AppConstent.KEY_NAME,name);

                DisplayFragment displayFragment = new DisplayFragment();
                displayFragment.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_relativeContanier,displayFragment,MessageFragment.class.getSimpleName());
                fragmentTransaction.addToBackStack(MessageFragment.class.getSimpleName());
                fragmentTransaction.commit();


            }


        });

    }

    @Override
    public void onResume() {
        super.onResume();
        nameEd.setText("");
    }
}
