package com.anukul.sqlitelogindemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anukul.sqlitelogindemo.ContactModel;
import com.anukul.sqlitelogindemo.R;

import java.util.ArrayList;

public class ReadContactAdapter extends RecyclerView.Adapter<ReadContactAdapter.ReadUserViewHolder>{

    private ArrayList<ContactModel> contactModelArrayList;

    public ReadContactAdapter(ArrayList<ContactModel> userModelArrayList) {
        this.contactModelArrayList = userModelArrayList;
    }

    public class ReadUserViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView lastName;
        TextView email;
        TextView phoneNo;
        TextView gender;
        public ReadUserViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.custom_layout_idTv);
            name = itemView.findViewById(R.id.custom_layout_nameTv);
            lastName = itemView.findViewById(R.id.custom_layout_lastNameTv);
            email = itemView.findViewById(R.id.custom_layout_emailTv);
            phoneNo = itemView.findViewById(R.id.custom_layout_phoneTv);
            gender = itemView.findViewById(R.id.custom_layout_genderTv);
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
        ContactModel contactModel = contactModelArrayList.get(position);

        holder.id.setText("ID : "+contactModel.getId()+"");
        holder.name.setText("Name : "+contactModel.getName());
        holder.lastName.setText("LastName : "+contactModel.getLastName());
        holder.email.setText("Email : "+contactModel.getEmail());
        holder.phoneNo.setText("PhoneNo : "+contactModel.getPhoneNo());
        holder.gender.setText("Gender : "+contactModel.getGender());

    }

    @Override
    public int getItemCount() {
        return contactModelArrayList.size();
    }


}
