package com.anukul.sqlitetest;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddEmployeeFragment extends Fragment implements View.OnClickListener {
    private EditText addEmployeeEd;
    private Button addEmployeeBtn;
    private Spinner departmentSpinner;
    ArrayAdapter<EmployeeModel> departmentAdapter;
    ArrayList<EmployeeModel> departmentArraylist;

    SQLiteDatabase sqLiteDatabase;

    public AddEmployeeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);
        departmentSpinner = view.findViewById(R.id.fragment_sqlite_departmentSpinner);

        EmloyeeDbHelper emloyeeDbHelper = new EmloyeeDbHelper(getActivity());
        EmployeeModel employeeModel = new EmployeeModel();
        departmentArraylist = new ArrayList<>();
        departmentArraylist = emloyeeDbHelper.getAllUser();
        //departmentAdapter = new ArrayAdapter<>(this,R.layout.department_name,R.id.department_name_Tv,departmentArraylist);
        departmentAdapter = new ArrayAdapter<>(getActivity(),R.layout.department_name,R.id.department_name_Tv,departmentArraylist);
        //employeeDetailsAdapter = new EmployeeDetailsAdapter(employeeModelArrayList);
        departmentSpinner.setAdapter(departmentAdapter);
        if (departmentArraylist.size() == 0) {

            Toast.makeText(getActivity(), " No Data", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        departmentSpinner = view.findViewById(R.id.fragment_sqlite_departmentSpinner);
        addEmployeeEd = view.findViewById(R.id.fragment_sqlite_addEmployeNameEd);
        addEmployeeBtn = view.findViewById(R.id.fragment_sqlite_addEmployeBtn);

        addEmployeeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_sqlite_addEmployeBtn:
                insertData();
                break;
        }
    }

    private void insertData() {
        String employeeName = addEmployeeEd.getText().toString().trim();

        if(TextUtils.isEmpty(employeeName)){
            addEmployeeEd.setError("Please Enter name");
        }else {
            EmloyeeDbHelper emloyeeDbHelper = new EmloyeeDbHelper(getActivity());
            sqLiteDatabase = emloyeeDbHelper.getWritableDatabase();

            Toast.makeText(getActivity(), "" + employeeName  , Toast.LENGTH_SHORT).show();
            Log.e("Data", employeeName);

            for (int i = 0; i < 2; i++) {
                emloyeeDbHelper.insertEmpData(new EmployeeModel(employeeName),
                        sqLiteDatabase);
            }
            emloyeeDbHelper.close();
            //empIdEd.setText("");
            addEmployeeEd.setText("");
            Toast.makeText(getActivity(), "Name Insert Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}