package com.anukul.sqlitetest;

public class EmployeeModel {
    private String addDepartment;
    private String addEmployee;
    private String employeeDetails;

    public EmployeeModel(String addDepartment, String addEmployee, String employeeDetails) {
        this.addDepartment = addDepartment;
        this.addEmployee = addEmployee;
        this.employeeDetails = employeeDetails;
    }

    public EmployeeModel() {
    }

    public String getAddDepartment() {
        return addDepartment;
    }

    public void setAddDepartment(String addDepartment) {
        this.addDepartment = addDepartment;
    }

    public String getAddEmployee() {
        return addEmployee;
    }

    public void setAddEmployee(String addEmployee) {
        this.addEmployee = addEmployee;
    }

    public String getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(String employeeDetails) {
        this.employeeDetails = employeeDetails;
    }
}
