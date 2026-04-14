package com.healthcare.service;

import com.healthcare.model.Appointment;
import com.healthcare.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository repo;

    public AppointmentService(AppointmentRepository repo) {
        this.repo = repo;
    }

    public List<Appointment> getAllAppointments()               { return repo.findAll(); }
    public Optional<Appointment> getAppointmentById(int id)    { return repo.findById(id); }
    public List<Appointment> getByDate(String date)            { return repo.findByDate(date); }
    public List<Appointment> getByPatient(int patientId)       { return repo.findByPatient(patientId); }
    public List<Appointment> getByDoctor(int doctorId)         { return repo.findByDoctor(doctorId); }
    public List<Appointment> getEmergencies()                  { return repo.findEmergencies(); }
    public List<Appointment> getByStatus(String status)        { return repo.findByStatus(status); }
    public List<Map<String, Object>> getStatusReport()         { return repo.countByStatus(); }
    public List<Map<String, Object>> getDoctorWorkload()       { return repo.doctorWorkload(); }

    public String bookAppointment(Appointment a) {
        if (a.getStatus() == null || a.getStatus().isBlank()) a.setStatus("Booked");
        if (a.getEmergencyFlag() == null || a.getEmergencyFlag().isBlank()) a.setEmergencyFlag("No");
        return repo.save(a) > 0 ? "Appointment booked successfully" : "Failed to book appointment";
    }

    public String updateStatus(int id, String status) {
        return repo.updateStatus(id, status) > 0 ? "Status updated to " + status : "Appointment not found";
    }

    public String updateAppointment(Appointment a) {
        return repo.update(a) > 0 ? "Appointment updated successfully" : "Appointment not found";
    }

    public String cancelAppointment(int id) {
        return repo.updateStatus(id, "Cancelled") > 0 ? "Appointment cancelled" : "Appointment not found";
    }

    public String deleteAppointment(int id) {
        return repo.delete(id) > 0 ? "Appointment deleted" : "Appointment not found";
    }
}
