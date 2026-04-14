package com.healthcare.service;

import com.healthcare.model.Prescription;
import com.healthcare.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PrescriptionRepository repo;

    public PrescriptionService(PrescriptionRepository repo) {
        this.repo = repo;
    }

    public List<Prescription> getAllPrescriptions()                   { return repo.findAll(); }
    public Optional<Prescription> getPrescriptionById(int id)        { return repo.findById(id); }
    public List<Prescription> getByAppointment(int appointmentId)    { return repo.findByAppointment(appointmentId); }

    public String addPrescription(Prescription p) {
        return repo.save(p) > 0 ? "Prescription added successfully" : "Failed to add prescription";
    }

    public String updatePrescription(Prescription p) {
        return repo.update(p) > 0 ? "Prescription updated successfully" : "Prescription not found";
    }

    public String deletePrescription(int id) {
        return repo.delete(id) > 0 ? "Prescription deleted" : "Prescription not found";
    }
}
