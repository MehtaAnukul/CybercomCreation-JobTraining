package com.anukul.listviewwithcontextmenu;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarListViewAdapter extends ArrayAdapter<String> {

    private List<String> carsList = new ArrayList<>();
    private Context context;

    public CarListViewAdapter( List<String> carsList, Context context) {
        super(context,R.layout.car_custom_layout,carsList);
        this.carsList = carsList;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
       View view =  inflater.inflate(R.layout.car_custom_layout,parent,false);
        TextView carName = view.findViewById(R.id.activitiy_main_carTv);
        carName.setText(carsList.get(position));
        return view;
    }

    public void removeItem(List<String> carsListItem){
        for (String i : carsListItem) {
            carsList.remove(i);
        }
        notifyDataSetChanged();
    }
}
