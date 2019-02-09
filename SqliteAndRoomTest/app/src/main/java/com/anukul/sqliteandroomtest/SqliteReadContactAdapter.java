package com.anukul.sqliteandroomtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SqliteReadContactAdapter extends RecyclerView.Adapter<SqliteReadContactAdapter.SqliteReadContactViewHolder>{

    private ArrayList<ContactModel> sqliteContactModelArraylist;

    public SqliteReadContactAdapter(ArrayList<ContactModel> sqliteContactModelArraylist) {
        this.sqliteContactModelArraylist = sqliteContactModelArraylist;
    }

    public class SqliteReadContactViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView fName;
        TextView lName;
        TextView phoneNo;
        TextView email;

        public SqliteReadContactViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.sqlite_customLayout_idTv);
            fName = itemView.findViewById(R.id.sqlite_customLayout_firstNameTv);
            lName = itemView.findViewById(R.id.sqlite_customLayout_lastNameTv);
            phoneNo = itemView.findViewById(R.id.sqlite_customLayout_phoneNoTv);
            email = itemView.findViewById(R.id.sqlite_customLayout_emailTv);

        }
    }


    @NonNull
    @Override
    public SqliteReadContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sqlite_custom_layout,viewGroup,false);
        return new SqliteReadContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SqliteReadContactViewHolder sqliteReadContactViewHolder, int position) {
        ContactModel contactModel = sqliteContactModelArraylist.get(position);

        sqliteReadContactViewHolder.id.setText("ID : "+contactModel.getId());
        sqliteReadContactViewHolder.fName.setText("FName : "+contactModel.getfName());
        sqliteReadContactViewHolder.lName.setText("LName : "+contactModel.getlName());
        sqliteReadContactViewHolder.phoneNo.setText("PhoneNo : "+contactModel.getPhoneNo());
        sqliteReadContactViewHolder.email.setText("Email : "+contactModel.getEmail());

    }

    @Override
    public int getItemCount() {
        return sqliteContactModelArraylist.size();
    }
    }
