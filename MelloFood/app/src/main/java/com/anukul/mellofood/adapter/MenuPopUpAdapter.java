package com.anukul.mellofood.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anukul.mellofood.listener.MallStallOnItemClickListener;
import com.anukul.mellofood.R;
import com.anukul.mellofood.model.MallStallModel;

import java.util.ArrayList;

public class MenuPopUpAdapter extends RecyclerView.Adapter<MenuPopUpAdapter.MenuPopUpViewHolder> {

    private ArrayList<MallStallModel> mallStallModelArrayList;
    private MallStallOnItemClickListener mallStallOnItemClickListener;

    public MenuPopUpAdapter(ArrayList<MallStallModel> mallStallModelArrayList, MallStallOnItemClickListener mallStallOnItemClickListener) {
        this.mallStallModelArrayList = mallStallModelArrayList;
        this.mallStallOnItemClickListener = mallStallOnItemClickListener;
    }

    public class MenuPopUpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mallStallTv;
        MallStallModel mallStallModel;

        public MenuPopUpViewHolder(@NonNull View itemView) {
            super(itemView);
            mallStallTv = itemView.findViewById(R.id.menu_popUpCustomLayout_Tv);

            itemView.setOnClickListener(this);
        }

        public void setData(MallStallModel data) {
            this.mallStallModel = data;

            mallStallTv.setText(mallStallModel.getMallStallName());
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
    public MenuPopUpViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_popup_custom_layout, viewGroup, false);
        return new MenuPopUpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuPopUpViewHolder menuPopUpViewHolder, int i) {
        MallStallModel mallStallModel = mallStallModelArrayList.get(i);

        menuPopUpViewHolder.setData(mallStallModel);
    }

    @Override
    public int getItemCount() {
        return mallStallModelArrayList.size();
    }


}
