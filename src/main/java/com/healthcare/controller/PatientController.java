package com.healthcare.controller;

import com.healthcare.model.Patient;
import com.healthcare.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Patient> getAll() {
        return service.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable int id) {
        return service.getPatientById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Patient> search(@RequestParam String q) {
        return service.search(q);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Patient patient) {
        return ResponseEntity.ok(service.addPatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Patient patient) {
        patient.setPatientId(id);
        return ResponseEntity.ok(service.updatePatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.deletePatient(id));
    }
}
