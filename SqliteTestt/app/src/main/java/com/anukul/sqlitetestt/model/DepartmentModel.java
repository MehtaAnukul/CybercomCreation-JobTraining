package com.anukul.sqlitetestt.model;

public class DepartmentModel {
    private int departmentId;
    private String departmentName;

    public DepartmentModel(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public DepartmentModel(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentModel() {
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
