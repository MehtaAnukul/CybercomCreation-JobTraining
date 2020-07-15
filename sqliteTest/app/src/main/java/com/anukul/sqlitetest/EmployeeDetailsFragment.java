package com.anukul.sqlitetest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class EmployeeDetailsFragment extends Fragment {
    private RecyclerView customEmpDetailsRecyclerView;
    private static ArrayList<EmployeeModel> employeeModelArrayList;
    private static EmployeeDetailsAdapter employeeDetailsAdapter;

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
        View view =  inflater.inflate(R.layout.fragment_employee_details, container, false);
        customEmpDetailsRecyclerView = view.findViewById(R.id.fragment_employeeDetails_recyclerView);

        EmloyeeDbHelper emloyeeDbHelper = new EmloyeeDbHelper(getActivity());
        employeeModelArrayList = emloyeeDbHelper.getAllUser();
        employeeDetailsAdapter = new EmployeeDetailsAdapter(employeeModelArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        customEmpDetailsRecyclerView.setLayoutManager(layoutManager);
        customEmpDetailsRecyclerView.setAdapter(employeeDetailsAdapter);
        if (employeeModelArrayList.size() == 0) {

            Toast.makeText(getActivity(), " No Data", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    public static EmployeeDetailsAdapter getEmployeeDetailsAdapter() {
        return employeeDetailsAdapter;
    }

    public static ArrayList<EmployeeModel> getEmployeeModelArrayList() {
        return employeeModelArrayList;
    }
}