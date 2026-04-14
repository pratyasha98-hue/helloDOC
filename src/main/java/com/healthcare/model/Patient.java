package com.healthcare.model;

public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String city;
    private String medicalHistory;

    public Patient() {}

    public Patient(int patientId, String name, int age, String gender,
                   String phone, String city, String medicalHistory) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.city = city;
        this.medicalHistory = medicalHistory;
    }

    public int getPatientId()             { return patientId; }
    public void setPatientId(int id)      { this.patientId = id; }
    public String getName()               { return name; }
    public void setName(String name)      { this.name = name; }
    public int getAge()                   { return age; }
    public void setAge(int age)           { this.age = age; }
    public String getGender()             { return gender; }
    public void setGender(String gender)  { this.gender = gender; }
    public String getPhone()              { return phone; }
    public void setPhone(String phone)    { this.phone = phone; }
    public String getCity()               { return city; }
    public void setCity(String city)      { this.city = city; }
    public String getMedicalHistory()              { return medicalHistory; }
    public void setMedicalHistory(String history)  { this.medicalHistory = history; }
}
