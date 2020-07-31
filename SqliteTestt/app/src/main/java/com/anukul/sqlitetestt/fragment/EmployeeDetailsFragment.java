package com.anukul.sqlitetestt.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.anukul.sqlitetestt.EmloyeeDbHelper;
import com.anukul.sqlitetestt.EmployeeDetailsAdapter;
import com.anukul.sqlitetestt.ItemClickListener;
import com.anukul.sqlitetestt.R;
import com.anukul.sqlitetestt.model.EmployeeModel;

import java.util.ArrayList;

public class EmployeeDetailsFragment extends Fragment implements ItemClickListener {

    private RecyclerView customEmpDetailsRecyclerView;
    private static ArrayList<EmployeeModel> employeeModelArrayList;
    private static EmployeeDetailsAdapter employeeDetailsAdapter;
    private Button deleteBtn;
    private Button editBtn;
    private FragmentManager fragmentManager;

    private ItemClickListener itemClickListener;


    public EmployeeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employee_details, container, false);
        customEmpDetailsRecyclerView = view.findViewById(R.id.fragment_employeeDetails_recyclerView);
        deleteBtn = view.findViewById(R.id.custom_layout_deleteBtn);
        editBtn = view.findViewById(R.id.custom_layout_editBtn);

        EmloyeeDbHelper emloyeeDbHelper = new EmloyeeDbHelper(getActivity());
        employeeModelArrayList = emloyeeDbHelper.getAllUser();
        employeeDetailsAdapter = new EmployeeDetailsAdapter(employeeModelArrayList,itemClickListener,getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        customEmpDetailsRecyclerView.setLayoutManager(layoutManager);
        customEmpDetailsRecyclerView.setAdapter(employeeDetailsAdapter);
        if (employeeModelArrayList.size() == 0) {

            Toast.makeText(getActivity(), " No Data", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static EmployeeDetailsAdapter getEmployeeDetailsAdapter() {
        return employeeDetailsAdapter;
    }

    public static ArrayList<EmployeeModel> getEmployeeModelArrayList() {
        return employeeModelArrayList;
    }


    @Override
    public void onItemClick(EmployeeModel employeeModel, View view, int position) {
        switch (view.getId()) {
            case R.id.custom_layout_editBtn:
                updateData(position, employeeModel);
                break;
            case R.id.custom_layout_deleteBtn:
                Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_SHORT).show();
                deleteData(position, employeeModel);
                break;
        }
    }

    private void deleteData(final int position, final EmployeeModel employeeModel) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getContext(),R.style.Theme_AppCompat_Light_Dialog_MinWidth);
        builder.setTitle("DELETE");
        builder.setMessage("Are you sure");
        builder.setCancelable(false);

        Log.e("TYTY",position+"--"+employeeModelArrayList.get(position));

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                employeeModelArrayList.remove(position);
                employeeDetailsAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Data Not Delete", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void updateData(int position, EmployeeModel employeeModel) {

        EmployeeDetailsFragment employeeDetailsFragment = new EmployeeDetailsFragment();
        EmployeeFragment addEmployeeFragment = new EmployeeFragment();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.replace(R.id.activity_main_fragment_container, addEmployeeFragment);
        //fragmentTransaction.addToBackStack(SqliteAddFragment.class.getSimpleName());
        fragmentTransaction1.hide(employeeDetailsFragment);
        fragmentTransaction1.commit();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            itemClickListener = (ItemClickListener ) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement MyInterface ");
        }
    }
}