package com.healthcare.controller;

import com.healthcare.model.Doctor;
import com.healthcare.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Doctor> getAll() {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable int id) {
        return service.getDoctorById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Doctor> search(@RequestParam String q) {
        return service.search(q);
    }

    @GetMapping("/department/{deptId}")
    public List<Doctor> getByDepartment(@PathVariable int deptId) {
        return service.getByDepartment(deptId);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(service.addDoctor(doctor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Doctor doctor) {
        doctor.setDoctorId(id);
        return ResponseEntity.ok(service.updateDoctor(doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.deleteDoctor(id));
    }
}
