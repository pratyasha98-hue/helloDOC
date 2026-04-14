-- ================================================
-- Healthcare Appointment System - Database Schema
-- ================================================

CREATE DATABASE IF NOT EXISTS healthcare_db;
USE healthcare_db;

-- Departments Table
CREATE TABLE IF NOT EXISTS departments (
    department_id   INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL
);

-- Patients Table
CREATE TABLE IF NOT EXISTS patients (
    patient_id      INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    age             INT NOT NULL,
    gender          VARCHAR(10) NOT NULL,
    phone           VARCHAR(15) NOT NULL,
    city            VARCHAR(50),
    medical_history TEXT
);

-- Doctors Table
CREATE TABLE IF NOT EXISTS doctors (
    doctor_id        INT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    specialization   VARCHAR(100) NOT NULL,
    department_id    INT NOT NULL,
    consultation_fee DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);

-- Appointments Table
CREATE TABLE IF NOT EXISTS appointments (
    appointment_id   INT AUTO_INCREMENT PRIMARY KEY,
    patient_id       INT NOT NULL,
    doctor_id        INT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status           VARCHAR(20) NOT NULL DEFAULT 'Booked',  -- Booked, Completed, Cancelled
    token_number     INT,
    emergency_flag   VARCHAR(5) NOT NULL DEFAULT 'No',       -- Yes / No
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (doctor_id)  REFERENCES doctors(doctor_id)
);

-- Prescriptions Table
CREATE TABLE IF NOT EXISTS prescriptions (
    prescription_id  INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id   INT NOT NULL,
    medicine_name    VARCHAR(100) NOT NULL,
    dosage           VARCHAR(100) NOT NULL,
    notes            TEXT,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
);

-- Billing Table
CREATE TABLE IF NOT EXISTS billing (
    bill_id          INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id   INT NOT NULL,
    amount           DECIMAL(10, 2) NOT NULL,
    payment_status   VARCHAR(20) NOT NULL DEFAULT 'Pending',  -- Paid / Pending
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
);
