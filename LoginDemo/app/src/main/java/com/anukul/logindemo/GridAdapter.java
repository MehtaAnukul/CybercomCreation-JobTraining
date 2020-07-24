package com.anukul.logindemo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.anukul.logindemo.model.GridModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    public List<GridModel> gridModelList;
    public Context context;

    public GridAdapter(List<GridModel> gridModelList, Context context) {
        this.gridModelList = gridModelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gridModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout,null);

        //find View
        ImageView imageView = view.findViewById(R.id.custom_layout_Img);

        //set data
        Glide.with(context)
                .load(gridModelList.get(position).getUrl())
                .into(imageView);

        return view;
    }
}
