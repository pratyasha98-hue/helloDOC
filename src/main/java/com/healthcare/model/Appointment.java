package com.healthcare.model;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String appointmentDate;   // "YYYY-MM-DD"
    private String appointmentTime;   // "HH:MM"
    private String status;            // Booked / Completed / Cancelled
    private int tokenNumber;
    private String emergencyFlag;     // Yes / No

    // Joined fields
    private String patientName;
    private String doctorName;

    public Appointment() {}

    public int getAppointmentId()                  { return appointmentId; }
    public void setAppointmentId(int id)           { this.appointmentId = id; }
    public int getPatientId()                      { return patientId; }
    public void setPatientId(int id)               { this.patientId = id; }
    public int getDoctorId()                       { return doctorId; }
    public void setDoctorId(int id)                { this.doctorId = id; }
    public String getAppointmentDate()             { return appointmentDate; }
    public void setAppointmentDate(String date)    { this.appointmentDate = date; }
    public String getAppointmentTime()             { return appointmentTime; }
    public void setAppointmentTime(String time)    { this.appointmentTime = time; }
    public String getStatus()                      { return status; }
    public void setStatus(String status)           { this.status = status; }
    public int getTokenNumber()                    { return tokenNumber; }
    public void setTokenNumber(int token)          { this.tokenNumber = token; }
    public String getEmergencyFlag()               { return emergencyFlag; }
    public void setEmergencyFlag(String flag)      { this.emergencyFlag = flag; }
    public String getPatientName()                 { return patientName; }
    public void setPatientName(String name)        { this.patientName = name; }
    public String getDoctorName()                  { return doctorName; }
    public void setDoctorName(String name)         { this.doctorName = name; }
}
