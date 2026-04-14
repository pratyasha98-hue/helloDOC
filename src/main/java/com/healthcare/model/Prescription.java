package com.healthcare.model;

public class Prescription {
    private int prescriptionId;
    private int appointmentId;
    private String medicineName;
    private String dosage;
    private String notes;

    public Prescription() {}

    public int getPrescriptionId()                   { return prescriptionId; }
    public void setPrescriptionId(int id)            { this.prescriptionId = id; }
    public int getAppointmentId()                    { return appointmentId; }
    public void setAppointmentId(int id)             { this.appointmentId = id; }
    public String getMedicineName()                  { return medicineName; }
    public void setMedicineName(String name)         { this.medicineName = name; }
    public String getDosage()                        { return dosage; }
    public void setDosage(String dosage)             { this.dosage = dosage; }
    public String getNotes()                         { return notes; }
    public void setNotes(String notes)               { this.notes = notes; }
}
