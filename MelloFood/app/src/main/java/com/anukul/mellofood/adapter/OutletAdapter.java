package com.anukul.mellofood.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anukul.mellofood.listener.OutletOnItemClickListener;
import com.anukul.mellofood.R;
import com.anukul.mellofood.model.OutletModel;

import java.util.ArrayList;

public class OutletAdapter extends RecyclerView.Adapter<OutletAdapter.OutletViewHolder>{

    private ArrayList<OutletModel> outletModelArrayList;
    private OutletOnItemClickListener outletOnItemClickListener;

    public OutletAdapter(ArrayList<OutletModel> outletModelArrayList, OutletOnItemClickListener outletOnItemClickListener) {
        this.outletModelArrayList = outletModelArrayList;
        this.outletOnItemClickListener = outletOnItemClickListener;
    }

    public class OutletViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mallName;
        ImageView mallImgRes;
        OutletModel outletModel;
        public OutletViewHolder(@NonNull View itemView) {
            super(itemView);
            mallName = itemView.findViewById(R.id.mall_custom_layout_mallNameTv);
            mallImgRes = itemView.findViewById(R.id.mall_custom_layout_ImgView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(outletOnItemClickListener != null){
                outletOnItemClickListener.outletOnItemClick(outletModel);
            }
        }

        public void setData(OutletModel data) {
            this.outletModel = data;

            mallName.setText(outletModel.getMallName());
            mallImgRes.setImageResource(outletModel.getMallImage());

        }
    }

    @NonNull
    @Override
    public OutletViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mall_custom_layout,viewGroup,false);
        return new OutletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutletViewHolder outletViewHolder, int i) {
        OutletModel outletModel = outletModelArrayList.get(i);

        outletViewHolder.setData(outletModel);
    }

    @Override
    public int getItemCount() {
        return outletModelArrayList.size();
    }


}
