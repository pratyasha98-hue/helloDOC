package com.healthcare.controller;

import com.healthcare.model.Department;
import com.healthcare.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Department> getAll() {
        return service.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable int id) {
        return service.getDepartmentById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Department dept) {
        return ResponseEntity.ok(service.addDepartment(dept));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Department dept) {
        dept.setDepartmentId(id);
        return ResponseEntity.ok(service.updateDepartment(dept));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.deleteDepartment(id));
    }
}
