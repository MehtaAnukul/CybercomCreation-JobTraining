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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anukul.sqlitetestt.EmloyeeDbHelper;
import com.anukul.sqlitetestt.R;
import com.anukul.sqlitetestt.model.DepartmentModel;
import com.anukul.sqlitetestt.model.EmployeeModel;


public class DepartmentFragment extends Fragment implements View.OnClickListener{

    private EditText empIdEd;
    private EditText empDepartmentEd;
    private Button addDepartmentBtn;

    SQLiteDatabase sqLiteDatabase;

    public DepartmentFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_department, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        empDepartmentEd = view.findViewById(R.id.fragment_department_nameEd);
        addDepartmentBtn = view.findViewById(R.id.fragment_deparment_addBtn);

        addDepartmentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_deparment_addBtn:
                insertData();
                break;
        }
    }

    private void insertData() {
        String departmentName = empDepartmentEd.getText().toString().trim();

        if(TextUtils.isEmpty(departmentName)){
            empDepartmentEd.setError("Please Enter name");
        }else {
            EmloyeeDbHelper emloyeeDbHelper = new EmloyeeDbHelper(getActivity());
            sqLiteDatabase = emloyeeDbHelper.getWritableDatabase();

            Toast.makeText(getActivity(), "" + departmentName  , Toast.LENGTH_SHORT).show();
            Log.e("Data", departmentName);


            emloyeeDbHelper.insertDepartmentData(new DepartmentModel(departmentName),
                    sqLiteDatabase);

            emloyeeDbHelper.close();
            //empIdEd.setText("");
            empDepartmentEd.setText("");
            Toast.makeText(getActivity(), "Insert Successfully", Toast.LENGTH_SHORT).show();

        }
    }
}