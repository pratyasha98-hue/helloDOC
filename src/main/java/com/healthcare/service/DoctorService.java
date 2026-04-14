package com.healthcare.service;

import com.healthcare.model.Doctor;
import com.healthcare.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public List<Doctor> getAllDoctors()              { return repo.findAll(); }
    public Optional<Doctor> getDoctorById(int id)   { return repo.findById(id); }
    public List<Doctor> search(String keyword)       { return repo.search(keyword); }
    public List<Doctor> getByDepartment(int deptId) { return repo.findByDepartment(deptId); }

    public String addDoctor(Doctor d) {
        return repo.save(d) > 0 ? "Doctor added successfully" : "Failed to add doctor";
    }

    public String updateDoctor(Doctor d) {
        return repo.update(d) > 0 ? "Doctor updated successfully" : "Doctor not found";
    }

    public String deleteDoctor(int id) {
        return repo.delete(id) > 0 ? "Doctor deleted" : "Doctor not found";
    }
}
