package com.anukul.paginglibrarydemo;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ItemAdapter extends PagedListAdapter<ItemModel,ItemAdapter.ItemViewHolder> {

    private Context context;

    protected ItemAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.custom_layout_imgView);
            textView  = itemView.findViewById(R.id.custom_layout_nameTv);

        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout,viewGroup,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        ItemModel itemModel = getItem(position);

        if(itemModel != null){
            Glide.with(context)
                    .load(itemModel.owner.profile_image)
                    .into(itemViewHolder.imageView);

            itemViewHolder.textView.setText(itemModel.owner.display_name);
        }else {
            Toast.makeText(context, "ItemModel is null", Toast.LENGTH_SHORT).show();
        }
    }


    private static DiffUtil.ItemCallback<ItemModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ItemModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull ItemModel oldItemModel, @NonNull ItemModel newItemModel) {
                    return oldItemModel.answer_id == newItemModel.answer_id;
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull ItemModel oldItemModel, @NonNull ItemModel newItemModel) {
                    return oldItemModel.equals(newItemModel);
                }
            };
}
