package com.anukul.sqlitetestt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anukul.sqlitetestt.model.DepartmentModel;
import com.anukul.sqlitetestt.model.EmployeeModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeDetailsAdapter extends RecyclerView.Adapter<EmployeeDetailsAdapter.ReadEmployeeViewHolder> {

    private ArrayList<EmployeeModel> employeeModelArrayList;
    private ArrayList<DepartmentModel> departmentModelArrayList;
    private ItemClickListener itemClickListener;
    private Context context;
    SQLiteDatabase sqLiteDatabase;

    public EmployeeDetailsAdapter(ArrayList<EmployeeModel> employeeModelArrayList, ItemClickListener itemClickListener) {
        this.employeeModelArrayList = employeeModelArrayList;
        this.itemClickListener = itemClickListener;
    }

    public EmployeeDetailsAdapter(ArrayList<EmployeeModel> employeeModelArrayList, ItemClickListener itemClickListener, Context context) {
        this.employeeModelArrayList = employeeModelArrayList;
        this.itemClickListener = itemClickListener;
        this.context = context;
    }

    public EmployeeDetailsAdapter() {
    }


    public class ReadEmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id;
        TextView empNameTv;
        TextView empDepartmantTv;
        Button editBtn;
        Button deleteBtn;

        EmployeeModel employeeModel;
        DepartmentModel departmentModel;
        int position;


        public ReadEmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.custom_layout_idTv);
            empNameTv = itemView.findViewById(R.id.custom_layout_empNameTv);
            empDepartmantTv = itemView.findViewById(R.id.custom_layout_empdepartmentTv);
            editBtn = itemView.findViewById(R.id.custom_layout_editBtn);
            deleteBtn = itemView.findViewById(R.id.custom_layout_deleteBtn);

            itemView.setOnClickListener(this);
            deleteBtn.setOnClickListener(this);
            // editBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                if (v.getId() == R.id.custom_layout_deleteBtn) {
                    //Toast.makeText(context, "" + employeeModelArrayList.get(position).getDepartmentName(), Toast.LENGTH_SHORT).show();
                    EmloyeeDbHelper emloyeeDbHelper = new EmloyeeDbHelper(context);
                    sqLiteDatabase = emloyeeDbHelper.getWritableDatabase();
                   // emloyeeDbHelper.deleteItem(employeeModelArrayList.get(position).getDepartmentName());
                    employeeModelArrayList.remove(position);
                    notifyDataSetChanged();
                }
                itemClickListener.onItemClick(employeeModel, v, position);
            }
        }

        public void setData(EmployeeModel employeeModel,DepartmentModel departmentModel, int position) {
            this.employeeModel = employeeModel;
            this.departmentModel = departmentModel;
            this.position = position;

            id.setText("ID : " + employeeModel.getEmpId() + "");
            empNameTv.setText("Emp Name : " + employeeModel.getEmployeeName());
            empDepartmantTv.setText("Department : " + departmentModel.getDepartmentName());
        }


    }

    @NonNull
    @Override
    public ReadEmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout, parent, false);
        return new ReadEmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadEmployeeViewHolder holder, int position) {
        EmployeeModel employeeModel = employeeModelArrayList.get(position);
        DepartmentModel departmentModel = departmentModelArrayList.get(position);

        holder.setData(employeeModel,departmentModel, position);
    }

    @Override
    public int getItemCount() {
        return employeeModelArrayList.size();
    }


}
