package com.anukul.broadcastdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ReadNumberAdapter extends RecyclerView.Adapter<ReadNumberAdapter.ReadNumberViewHolder> {

    private ArrayList<NumbarModel> numbarModelArrayList;

    public ReadNumberAdapter(ArrayList<NumbarModel> numbarModelArrayList) {
        this.numbarModelArrayList = numbarModelArrayList;
    }

    public class ReadNumberViewHolder extends RecyclerView.ViewHolder {
        TextView idTv;
        TextView numberTv;
        public ReadNumberViewHolder(@NonNull View itemView) {
            super(itemView);

            idTv = itemView.findViewById(R.id.number_customLayout_idTv);
            numberTv = itemView.findViewById(R.id.number_customLayout_numberTv);

        }
    }

    @NonNull
    @Override
    public ReadNumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.number_custom_layout,viewGroup,false);
        return new ReadNumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadNumberViewHolder readNumberViewHolder, int i) {
        NumbarModel numbarModel = numbarModelArrayList.get(i);

        readNumberViewHolder.idTv.setText(Integer.toString(numbarModel.getId()));
        readNumberViewHolder.numberTv.setText(numbarModel.getNumber());
    }

    @Override
    public int getItemCount() {
        return numbarModelArrayList.size();
    }


}
