package com.anukul.retrofitrecycleviewdemo;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieModel> movieModelArrayList;
    private ItemClickListener itemClickListener;
    private Context context;

    public MovieAdapter(ArrayList<MovieModel> movieModelArrayList, ItemClickListener itemClickListener) {
        this.movieModelArrayList = movieModelArrayList;
        this.itemClickListener = itemClickListener;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.onItemClick(movieModel,view);
            }
        }

        public void setData(MovieModel data) {
            this.movieModel = data;

            textViewTitle.setText(movieModel.getTitle());
            textViewLanguage.setText(movieModel.getLanguage());
            textViewType.setText(movieModel.getType());
            textViewRating.setText(movieModel.getRating());
            textViewLikePercent.setText(movieModel.getLike_percent());
            textViewVotesCount.setText(movieModel.getVote_count());
            textViewIsNew.setText(movieModel.getIs_new());

            Glide.with(context)
                    .load(movieModel.getImage())
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
        MovieModel movieModel = movieModelArrayList.get(i);

        movieViewHolder.setData(movieModel);
    }

    @Override
    public int getItemCount() {
        return movieModelArrayList.size();
    }

}

