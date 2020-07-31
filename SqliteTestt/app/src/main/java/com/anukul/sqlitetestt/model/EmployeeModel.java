package com.anukul.sqlitetestt.model;

public class EmployeeModel {
    private int departmentId;
    private int empId;
    private String employeeName;

    public EmployeeModel(int departmentId, int empId, String employeeName) {
        this.departmentId = departmentId;
        this.empId = empId;
        this.employeeName = employeeName;
    }

    public EmployeeModel() {
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
