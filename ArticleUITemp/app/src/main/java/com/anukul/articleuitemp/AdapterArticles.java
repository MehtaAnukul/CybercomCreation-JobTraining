package com.anukul.articleuitemp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterArticles extends RecyclerView.Adapter<AdapterArticles.ViewHolder> {

    ArrayList<String> list;
    Context context;

    public AdapterArticles(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgArticle;
        public TextView tvTitle,tvDesc;
        public Button btReadMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArticle  = itemView.findViewById(R.id.imgArticle);
            tvTitle     = itemView.findViewById(R.id.tvTitle);
            tvDesc      = itemView.findViewById(R.id.tvDesc);
            btReadMore  = itemView.findViewById(R.id.btReadMore);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_articles, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).toString());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


}
