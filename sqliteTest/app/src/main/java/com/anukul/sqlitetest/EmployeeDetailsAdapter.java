package com.anukul.sqlitetest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeDetailsAdapter extends RecyclerView.Adapter<EmployeeDetailsAdapter.ReadEmployeeViewHolder> {

    private ArrayList<EmployeeModel> employeeModelArrayList;

    public EmployeeDetailsAdapter() {
    }

    public EmployeeDetailsAdapter(ArrayList<EmployeeModel> employeeModelArrayList) {
        this.employeeModelArrayList = employeeModelArrayList;
    }

    public class ReadEmployeeViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView empNameTv;
        TextView empDepartmantTv;
        Button editBtn;
        Button deleteBtn;


        public ReadEmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.custom_layout_idTv);
            empNameTv = itemView.findViewById(R.id.custom_layout_empNameTv);
            empDepartmantTv = itemView.findViewById(R.id.custom_layout_empdepartmentTv);
            editBtn = itemView.findViewById(R.id.custom_layout_editBtn);
            deleteBtn = itemView.findViewById(R.id.custom_layout_deleteBtn);

        }
    }
    @NonNull
    @Override
    public ReadEmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        return new ReadEmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadEmployeeViewHolder holder, int position) {
        EmployeeModel employeeModel = employeeModelArrayList.get(position);

        holder.id.setText("ID : " + employeeModel.getId() + "");
        holder.empNameTv.setText("Emp Name : " + employeeModel.getEmployeeName());
        holder.empDepartmantTv.setText("Department : " + employeeModel.getDepartmentName());

    }

    @Override
    public int getItemCount() {
        return employeeModelArrayList.size();
    }


}
