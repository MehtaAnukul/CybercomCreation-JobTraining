package com.anukul.sqlitetestt.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.anukul.sqlitetestt.EmloyeeDbHelper;
import com.anukul.sqlitetestt.R;
import com.anukul.sqlitetestt.model.DepartmentModel;
import com.anukul.sqlitetestt.model.EmployeeModel;

import java.util.ArrayList;


public class EmployeeFragment extends Fragment implements View.OnClickListener {

    private EditText addEmployeeEd;
    private Button addEmployeeBtn;
    private Spinner departmentSpinner;
    private String departmentNameString;
    ArrayAdapter<String> departmentAdapter;
    ArrayList<DepartmentModel> departmentArraylist;
    ArrayList<String> departmentnamelistStringArrayList;

    SQLiteDatabase sqLiteDatabase;



    public EmployeeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_employee, container, false);
        departmentSpinner = view.findViewById(R.id.fragment_employee_departmentNameSpinner);

        EmloyeeDbHelper emloyeeDbHelper = new EmloyeeDbHelper(getActivity());
        EmployeeModel employeeModel = new EmployeeModel();
        departmentArraylist = new ArrayList<>();
        departmentnamelistStringArrayList = new ArrayList<>();
        departmentArraylist = emloyeeDbHelper.getAllDepartment();

        for (DepartmentModel model : departmentArraylist) {
            departmentnamelistStringArrayList.add(model.getDepartmentName());
        }

        //departmentAdapter = new ArrayAdapter<>(this,R.layout.department_name,R.id.department_name_Tv,departmentArraylist);
        departmentAdapter = new ArrayAdapter<>(getActivity(), R.layout.department_name, R.id.department_name_Tv, departmentnamelistStringArrayList);
        //employeeDetailsAdapter = new EmployeeDetailsAdapter(employeeModelArrayList);
        departmentSpinner.setAdapter(departmentAdapter);
        Log.e("TYTY", departmentArraylist.get(0).getDepartmentName() + "");
        if (departmentArraylist.size() == 0) {

            Toast.makeText(getActivity(), " No Data", Toast.LENGTH_SHORT).show();
        }

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departmentNameString=departmentnamelistStringArrayList.get(position);
                Log.e("TYTY",departmentnamelistStringArrayList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        departmentSpinner = view.findViewById(R.id.fragment_employee_departmentNameSpinner);
        addEmployeeEd = view.findViewById(R.id.fragment_employee_nameEd);
        addEmployeeBtn = view.findViewById(R.id.fragment_employee_addBtn);

        addEmployeeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_employee_addBtn:
                insertData();
                break;
        }
    }

    private void insertData() {
        String employeeName = addEmployeeEd.getText().toString().trim();

        if (TextUtils.isEmpty(employeeName)) {
            addEmployeeEd.setError("Please Enter name");
        } else {
            EmloyeeDbHelper emloyeeDbHelper = new EmloyeeDbHelper(getActivity());
            sqLiteDatabase = emloyeeDbHelper.getWritableDatabase();

            Toast.makeText(getActivity(), "" + employeeName, Toast.LENGTH_SHORT).show();
            Log.e("Data", employeeName);

          /*  emloyeeDbHelper.insertEmpData(new EmployeeModel(departmentNameString,employeeName),
                    sqLiteDatabase);*/

            emloyeeDbHelper.close();
            //empIdEd.setText("");
            addEmployeeEd.setText("");
            Toast.makeText(getActivity(), "Name Insert Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}