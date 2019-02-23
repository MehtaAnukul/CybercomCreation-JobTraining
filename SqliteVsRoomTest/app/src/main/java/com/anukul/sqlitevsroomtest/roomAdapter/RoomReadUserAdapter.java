package com.anukul.sqlitevsroomtest.roomAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.anukul.sqlitevsroomtest.R;
import com.anukul.sqlitevsroomtest.roomModel.UserModel;
import com.anukul.sqlitevsroomtest.sqlite.model.ContactModel;

import java.util.ArrayList;

public class RoomReadUserAdapter extends RecyclerView.Adapter<RoomReadUserAdapter.RoomReadUserViewHolder>{

    private ArrayList<UserModel> userModelArrayList;

    public RoomReadUserAdapter(ArrayList<UserModel> userModelArrayList) {
        this.userModelArrayList = userModelArrayList;
    }
    public class RoomReadUserViewHolder extends RecyclerView.ViewHolder {
        TextView idTv;
        TextView firstNameTv;
        TextView lastNameTv;
        TextView phoneNoTv;
        TextView emailTv;
        public RoomReadUserViewHolder(@NonNull View itemView) {
            super(itemView);

            idTv = itemView.findViewById(R.id.room_customLayout_idTv);
            firstNameTv = itemView.findViewById(R.id.room_customLayout_fNameTv);
            lastNameTv  = itemView.findViewById(R.id.room_customLayout_lNameTv);
            phoneNoTv = itemView.findViewById(R.id.room_customLayout_phoneNoTv);
            emailTv = itemView.findViewById(R.id.room_customLayout_emailTv);


        }
    }
    @NonNull
    @Override
    public RoomReadUserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.room_custom_layout,viewGroup,false);
        return new RoomReadUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomReadUserViewHolder roomReadUserViewHolder, int i) {
        UserModel userModel = userModelArrayList.get(i);

        roomReadUserViewHolder.idTv.setText("ID : "+userModel.getId());
        roomReadUserViewHolder.firstNameTv.setText("F_Name : "+userModel.getName());
        roomReadUserViewHolder.lastNameTv.setText("L_Name : "+userModel.getLastName());
        roomReadUserViewHolder.phoneNoTv.setText("PhoneNo : "+userModel.getPhoneNo());
        roomReadUserViewHolder.emailTv.setText("Email : "+userModel.getEmail());

    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }


    public void updateList(ArrayList<UserModel> searchUserArraylist) {
        userModelArrayList = new ArrayList<>();
        userModelArrayList.addAll(searchUserArraylist);
        notifyDataSetChanged();
    }
}
