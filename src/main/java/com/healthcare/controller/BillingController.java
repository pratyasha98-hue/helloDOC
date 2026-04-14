package com.healthcare.controller;

import com.healthcare.model.Billing;
import com.healthcare.service.BillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/billing")
@CrossOrigin(origins = "*")
public class BillingController {

    private final BillingService service;

    public BillingController(BillingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Billing> getAll() {
        return service.getAllBills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billing> getById(@PathVariable int id) {
        return service.getBillById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<Billing> getByStatus(@PathVariable String status) {
        return service.getByStatus(status);
    }

    @GetMapping("/reports/revenue")
    public Map<String, Double> revenueReport() {
        return service.getRevenueReport();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Billing billing) {
        return ResponseEntity.ok(service.addBill(billing));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Billing billing) {
        billing.setBillId(id);
        return ResponseEntity.ok(service.updateBill(billing));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<String> markPaid(@PathVariable int id) {
        return ResponseEntity.ok(service.updatePaymentStatus(id, "Paid"));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable int id, @RequestParam String status) {
        return ResponseEntity.ok(service.updatePaymentStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.deleteBill(id));
    }
}
