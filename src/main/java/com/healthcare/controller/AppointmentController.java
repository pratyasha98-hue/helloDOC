package com.healthcare.controller;

import com.healthcare.model.Appointment;
import com.healthcare.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Appointment> getAll() {
        return service.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable int id) {
        return service.getAppointmentById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/date/{date}")
    public List<Appointment> getByDate(@PathVariable String date) {
        return service.getByDate(date);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getByPatient(@PathVariable int patientId) {
        return service.getByPatient(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getByDoctor(@PathVariable int doctorId) {
        return service.getByDoctor(doctorId);
    }

    @GetMapping("/emergency")
    public List<Appointment> getEmergencies() {
        return service.getEmergencies();
    }

    @GetMapping("/status/{status}")
    public List<Appointment> getByStatus(@PathVariable String status) {
        return service.getByStatus(status);
    }

    @PostMapping
    public ResponseEntity<String> book(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(service.bookAppointment(appointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Appointment appointment) {
        appointment.setAppointmentId(id);
        return ResponseEntity.ok(service.updateAppointment(appointment));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable int id, @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<String> cancel(@PathVariable int id) {
        return ResponseEntity.ok(service.cancelAppointment(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.deleteAppointment(id));
    }

    @GetMapping("/reports/status")
    public List<Map<String, Object>> statusReport() {
        return service.getStatusReport();
    }

    @GetMapping("/reports/workload")
    public List<Map<String, Object>> workloadReport() {
        return service.getDoctorWorkload();
    }
}
