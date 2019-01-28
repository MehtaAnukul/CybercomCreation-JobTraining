package com.anukul.roomdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anukul.roomdemo.model.UserModel;

import java.util.ArrayList;

public class ReadUserAdapter extends RecyclerView.Adapter<ReadUserAdapter.ReadUserViewHolder>{

    private ArrayList<UserModel> userModelArrayList;

    public ReadUserAdapter(ArrayList<UserModel> userModelArrayList) {
        this.userModelArrayList = userModelArrayList;
    }

    public class ReadUserViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView email;
        public ReadUserViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.custom_layout_idTv);
            name = itemView.findViewById(R.id.custom_layout_nameTv);
            email = itemView.findViewById(R.id.custom_layout_emailTv);
        }
    }
    @NonNull
    @Override
    public ReadUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);

        return new ReadUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadUserViewHolder holder, int position) {
        UserModel userModel = userModelArrayList.get(position);

        holder.id.setText("ID : "+userModel.getId()+"");
        holder.name.setText("Name : "+userModel.getName());
        holder.email.setText("Email : "+userModel.getEmail());

    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }


}
