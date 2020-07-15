package com.anukul.sqlitetest;

public class EmployeeModel {
    private int id;
    private String departmentName;
    private String employeeName;

    public EmployeeModel(int id, String departmentName, String employeeName) {
        this.id = id;
        this.departmentName = departmentName;
        this.employeeName = employeeName;
    }

    public EmployeeModel() {
    }

    public EmployeeModel(String departmentName, String employeeName) {
        this.departmentName = departmentName;
        this.employeeName = employeeName;
    }

    public EmployeeModel(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
