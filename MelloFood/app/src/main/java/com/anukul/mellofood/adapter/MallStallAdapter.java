package com.anukul.mellofood.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anukul.mellofood.listener.MallStallOnItemClickListener;
import com.anukul.mellofood.R;
import com.anukul.mellofood.model.MallStallModel;

import java.util.ArrayList;

public class MallStallAdapter extends RecyclerView.Adapter<MallStallAdapter.MallStallViewHolder> {

    private ArrayList<MallStallModel> mallStallModelArrayList;
    private MallStallOnItemClickListener mallStallOnItemClickListener;

    public MallStallAdapter(ArrayList<MallStallModel> mallStallModelArrayList, MallStallOnItemClickListener mallStallOnItemClickListener) {
        this.mallStallModelArrayList = mallStallModelArrayList;
        this.mallStallOnItemClickListener = mallStallOnItemClickListener;
    }

    public class MallStallViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mallStallNameTv;
        ImageView mallStallImgRes;
        MallStallModel mallStallModel;

        public MallStallViewHolder(@NonNull View itemView) {
            super(itemView);

            mallStallNameTv = itemView.findViewById(R.id.mallStall_customLayout_stallTitle);
            mallStallImgRes = itemView.findViewById(R.id.mallStall_customLayout_ImgView);

            itemView.setOnClickListener(this);
        }

        public void setData(MallStallModel data) {
            this.mallStallModel = data;

            mallStallNameTv.setText(mallStallModel.getMallStallName());
            mallStallImgRes.setImageResource(mallStallModel.getMallStallImgRes());
        }

        @Override
        public void onClick(View view) {
            if (mallStallOnItemClickListener != null) {
                mallStallOnItemClickListener.mallStallOnItemClick(mallStallModel);
            }
        }
    }

    @NonNull
    @Override
    public MallStallViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mall_stall_custom_layout, viewGroup, false);
        return new MallStallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MallStallViewHolder mallStallViewHolder, int i) {
        MallStallModel mallStallModel = mallStallModelArrayList.get(i);

        mallStallViewHolder.setData(mallStallModel);
    }

    @Override
    public int getItemCount() {
        return mallStallModelArrayList.size();
    }
}
