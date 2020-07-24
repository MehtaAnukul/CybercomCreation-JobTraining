package com.anukul.logindemo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.anukul.logindemo.model.ImageModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    public List<ImageModel> imageModelList;
    public Context context;

    public GridAdapter(List<ImageModel> imageModelList, Context context) {
        this.imageModelList = imageModelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageModelList.size();
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
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);

        //find View
        ImageView imageView = view.findViewById(R.id.custom_layout_Img);

        //set data
        Glide.with(context)
                .load(imageModelList.get(position).getImages())
                .into(imageView);

        return view;
    }
}
