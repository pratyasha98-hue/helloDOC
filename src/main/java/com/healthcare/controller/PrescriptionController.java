package com.healthcare.controller;

import com.healthcare.model.Prescription;
import com.healthcare.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin(origins = "*")
public class PrescriptionController {

    private final PrescriptionService service;

    public PrescriptionController(PrescriptionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Prescription> getAll() {
        return service.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getById(@PathVariable int id) {
        return service.getPrescriptionById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/appointment/{appointmentId}")
    public List<Prescription> getByAppointment(@PathVariable int appointmentId) {
        return service.getByAppointment(appointmentId);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Prescription prescription) {
        return ResponseEntity.ok(service.addPrescription(prescription));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Prescription prescription) {
        prescription.setPrescriptionId(id);
        return ResponseEntity.ok(service.updatePrescription(prescription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.deletePrescription(id));
    }
}
