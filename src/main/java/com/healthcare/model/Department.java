package com.healthcare.model;

public class Department {
    private int departmentId;
    private String departmentName;

    public Department() {}

    public Department(int departmentId, String departmentName) {
        this.departmentId   = departmentId;
        this.departmentName = departmentName;
    }

    public int getDepartmentId()                  { return departmentId; }
    public void setDepartmentId(int id)           { this.departmentId = id; }
    public String getDepartmentName()             { return departmentName; }
    public void setDepartmentName(String name)    { this.departmentName = name; }
}
