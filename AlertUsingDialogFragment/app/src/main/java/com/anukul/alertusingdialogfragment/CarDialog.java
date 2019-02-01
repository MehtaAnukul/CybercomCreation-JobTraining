package com.anukul.alertusingdialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;

public class CarDialog extends DialogFragment {

    private ArrayList<String> carSelectedArrayList;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        carSelectedArrayList = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select your Car");

        builder.setMultiChoiceItems(R.array.car, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                String carItem[] = getActivity().getResources().getStringArray(R.array.car);

                if (isChecked) {
                    carSelectedArrayList.add(carItem[i]);
                } else if (carSelectedArrayList.contains(carItem[i])) {
                    carSelectedArrayList.remove(carItem[i]);
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String final_selection = " ";

                for (int item = 0; item < carSelectedArrayList.size(); item++) {
                    final_selection = final_selection + "\n" + carSelectedArrayList.get(item);
                }
                /*for (String item : carSelectedArrayList) {
                    final_selection = final_selection+"\n"+item;
                }*/
                Toast.makeText(getActivity(), "Selected :" + final_selection, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Not Selected", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.show();
    }
}
