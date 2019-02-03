package com.anukul.listviewcontextactioncheckbox;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarListViewAdapter extends ArrayAdapter<String> {

    private List<String> carsList = new ArrayList<>();
    private Context context;

    public CarListViewAdapter(List<String> carsList, Context context) {
        super(context, R.layout.car_custom_layout, carsList);
        this.carsList = carsList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.car_custom_layout, parent, false);
        TextView carName = view.findViewById(R.id.activitiy_main_carTv);
        carName.setText(carsList.get(position));

        CheckBox checkBox = view.findViewById(R.id.activity_main_checkbox);
        checkBox.setTag(position);
        if(MainActivity.isActionMode){
            checkBox.setVisibility(View.VISIBLE);
        }else{
            checkBox.setVisibility(View.GONE);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int position = (int)compoundButton.getTag();

                if(MainActivity.userSelectionList.contains(carsList.get(position))){
                    MainActivity.userSelectionList.remove(carsList.get(position));
                }else{
                    MainActivity.userSelectionList.add(carsList.get(position));
                }
                MainActivity.actionMode.setTitle(MainActivity.userSelectionList.size() + " item Selected");
            }
        });
        return view;
    }

    public void removeItem(List<String> carsListItem) {
        for (String i : carsListItem) {
            carsList.remove(i);
        }
        notifyDataSetChanged();
    }
}
