package com.healthcare.model;

public class Doctor {
    private int doctorId;
    private String name;
    private String specialization;
    private int departmentId;
    private double consultationFee;

    // Joined field (from departments table)
    private String departmentName;

    public Doctor() {}

    public Doctor(int doctorId, String name, String specialization,
                  int departmentId, double consultationFee) {
        this.doctorId        = doctorId;
        this.name            = name;
        this.specialization  = specialization;
        this.departmentId    = departmentId;
        this.consultationFee = consultationFee;
    }

    public int getDoctorId()                        { return doctorId; }
    public void setDoctorId(int id)                 { this.doctorId = id; }
    public String getName()                         { return name; }
    public void setName(String name)                { this.name = name; }
    public String getSpecialization()               { return specialization; }
    public void setSpecialization(String spec)      { this.specialization = spec; }
    public int getDepartmentId()                    { return departmentId; }
    public void setDepartmentId(int id)             { this.departmentId = id; }
    public double getConsultationFee()              { return consultationFee; }
    public void setConsultationFee(double fee)      { this.consultationFee = fee; }
    public String getDepartmentName()               { return departmentName; }
    public void setDepartmentName(String name)      { this.departmentName = name; }
}
