package com.anukul.alertusingdialogfragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;

public class NewCarDialog extends DialogFragment {
    CarsSelectionListener carsSelectionListener;
    private ArrayList<String> selectionArrayList;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        selectionArrayList = new ArrayList<>();

        final String carItem[] = getActivity().getResources().getStringArray(R.array.car);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMultiChoiceItems(carItem, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                if (isChecked) {
                    selectionArrayList.add(carItem[i]);
                } else if (selectionArrayList.contains(carItem[i])) {
                    selectionArrayList.remove(carItem[i]);
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                carsSelectionListener.onCarSelected(selectionArrayList);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            carsSelectionListener = (CarsSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "Must implement CarsSelectionListener method");
        }
    }

    public interface CarsSelectionListener {
        public void onCarSelected(ArrayList<String> finalCarArrayList);
    }
}
