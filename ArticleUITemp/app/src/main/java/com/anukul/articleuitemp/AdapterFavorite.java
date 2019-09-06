package com.anukul.articleuitemp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder>  {

    ArrayList<String> list;
    Context context;

    public AdapterFavorite(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgArticle;
        public TextView tvTitle,tvDesc;
        public RelativeLayout rl1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgArticle  = itemView.findViewById(R.id.imgArticle);
            tvTitle     = itemView.findViewById(R.id.tvTitle);
            tvDesc      = itemView.findViewById(R.id.tvDesc);
            rl1         = itemView.findViewById(R.id.rl1);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_fav_article, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context,R.anim.layoutanim);
        viewHolder.tvTitle.setText(list.get(i).toString());
        viewHolder.rl1.setLayoutAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
