package com.healthcare.service;

import com.healthcare.model.Department;
import com.healthcare.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    public List<Department> getAllDepartments()              { return repo.findAll(); }
    public Optional<Department> getDepartmentById(int id)   { return repo.findById(id); }

    public String addDepartment(Department d) {
        return repo.save(d) > 0 ? "Department added successfully" : "Failed to add department";
    }

    public String updateDepartment(Department d) {
        return repo.update(d) > 0 ? "Department updated successfully" : "Department not found";
    }

    public String deleteDepartment(int id) {
        return repo.delete(id) > 0 ? "Department deleted" : "Department not found";
    }
}
