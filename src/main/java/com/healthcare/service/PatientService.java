package com.healthcare.service;

import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public List<Patient> getAllPatients()            { return repo.findAll(); }
    public Optional<Patient> getPatientById(int id) { return repo.findById(id); }
    public List<Patient> search(String keyword)      { return repo.search(keyword); }

    public String addPatient(Patient p) {
        int rows = repo.save(p);
        return rows > 0 ? "Patient added successfully" : "Failed to add patient";
    }

    public String updatePatient(Patient p) {
        int rows = repo.update(p);
        return rows > 0 ? "Patient updated successfully" : "Patient not found";
    }

    public String deletePatient(int id) {
        int rows = repo.delete(id);
        return rows > 0 ? "Patient deleted" : "Patient not found";
    }
}
