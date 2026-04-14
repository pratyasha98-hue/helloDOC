package com.healthcare.model;

public class Billing {
    private int billId;
    private int appointmentId;
    private double amount;
    private String paymentStatus;  // Paid / Pending

    // Joined fields
    private String patientName;
    private String doctorName;
    private String appointmentDate;

    public Billing() {}

    public int getBillId()                         { return billId; }
    public void setBillId(int id)                  { this.billId = id; }
    public int getAppointmentId()                  { return appointmentId; }
    public void setAppointmentId(int id)           { this.appointmentId = id; }
    public double getAmount()                      { return amount; }
    public void setAmount(double amount)           { this.amount = amount; }
    public String getPaymentStatus()               { return paymentStatus; }
    public void setPaymentStatus(String status)    { this.paymentStatus = status; }
    public String getPatientName()                 { return patientName; }
    public void setPatientName(String name)        { this.patientName = name; }
    public String getDoctorName()                  { return doctorName; }
    public void setDoctorName(String name)         { this.doctorName = name; }
    public String getAppointmentDate()             { return appointmentDate; }
    public void setAppointmentDate(String date)    { this.appointmentDate = date; }
}
