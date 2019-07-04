package com.anukul.retrofitrecycleviewdemo;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieModel> movieModelList;
    private int rowLayout;
    private Context context;

    public MovieAdapter(List<MovieModel> movieModelList, int rowLayout, Context context) {
        this.movieModelList = movieModelList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder  {
        TextView textViewIsNew;
        TextView textViewRating;
        ImageView imageViewLike;
        ImageView imageView;
        TextView textViewLikePercent;
        TextView textViewVotesCount;
        TextView textViewTitle;
        TextView textViewLanguage;
        TextView textViewType;
        MovieModel movieModel;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIsNew = itemView.findViewById(R.id.custom_layout_textViewIsNew);
            textViewRating = itemView.findViewById(R.id.custom_layout_textViewRating);
            imageViewLike = itemView.findViewById(R.id.custom_layout_imageViewLike);
            imageView = itemView.findViewById(R.id.custom_layout_imageView);
            textViewLikePercent = itemView.findViewById(R.id.custom_layout_textViewLikePercent);
            textViewVotesCount = itemView.findViewById(R.id.custom_layout_textViewVotesCount);
            textViewTitle = itemView.findViewById(R.id.custom_layout_textViewTitle);
            textViewLanguage = itemView.findViewById(R.id.custom_layout_textViewLanguage);
            textViewType = itemView.findViewById(R.id.custom_layout_textViewType);

            //itemView.setOnClickListener(this);
        }

       /* @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.onItemClick(movieModel,view);
            }
        }*/

        public void setData(MovieModel data) {
            this.movieModel = data;

            textViewTitle.setText(movieModel.getName());
            textViewLanguage.setText(movieModel.getRealname());

            Glide.with(context)
                    .load(movieModel.getImageurl())
                    .placeholder(R.drawable.ic_like)
                    .into(imageView);
        }
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout_movie,viewGroup,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        MovieModel movieModel = movieModelList.get(i);

        movieViewHolder.setData(movieModel);
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

}

