package com.anukul.contextmenuwithrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder>{

    private ArrayList<FruitModel> fruitModelArrayList;
    private ItemClickListener itemClickListener;

    public FruitAdapter(ArrayList<FruitModel> fruitModelArrayList, ItemClickListener itemClickListener) {
        this.fruitModelArrayList = fruitModelArrayList;
        this.itemClickListener = itemClickListener;
    }

    public class FruitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnCreateContextMenuListener {
        TextView fruitName;
        ImageView fruitImgRes;
        FruitModel fruitModel;
        CardView cardView;
        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);

            fruitName = itemView.findViewById(R.id.activity_main_fruitName);
            fruitImgRes = itemView.findViewById(R.id.activity_main_fruitImg);
            cardView = itemView.findViewById(R.id.fruit_custom_cardView);
            itemView.setOnClickListener(this);
            cardView.setOnCreateContextMenuListener(this);
        }

        public void setData(FruitModel data) {
            this.fruitModel = data;

            fruitName.setText(data.getFruitName());
            fruitImgRes.setImageResource(data.getFruitImgRes());
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(fruitModel);
            }
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select an Option");
            contextMenu.add(this.getAdapterPosition(),11,0,"Delete this Item");
            contextMenu.add(this.getAdapterPosition(),12,1,"Add to Wish List");
        }
    }
    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_custom_layout,viewGroup,false);

        return new FruitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder fruitViewHolder, int i) {

        FruitModel fruitModel = fruitModelArrayList.get(i);

        fruitViewHolder.setData(fruitModel);

    }

    @Override
    public int getItemCount() {
        return fruitModelArrayList.size();
    }

    public void removeItem(int position){
        fruitModelArrayList.remove(position);
        notifyDataSetChanged();
    }
}
