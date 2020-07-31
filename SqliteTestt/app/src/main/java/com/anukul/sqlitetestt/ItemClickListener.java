package com.anukul.sqlitetestt;

import android.view.View;

import com.anukul.sqlitetestt.model.EmployeeModel;

public interface ItemClickListener {
    public void onItemClick(EmployeeModel employeeModel, View view, int position);
}
