package com.healthcare.service;

import com.healthcare.model.Billing;
import com.healthcare.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BillingService {

    private final BillingRepository repo;

    public BillingService(BillingRepository repo) {
        this.repo = repo;
    }

    public List<Billing> getAllBills()             { return repo.findAll(); }
    public Optional<Billing> getBillById(int id)  { return repo.findById(id); }
    public List<Billing> getByStatus(String s)    { return repo.findByStatus(s); }

    public Map<String, Double> getRevenueReport() {
        return Map.of(
            "totalRevenue",  repo.totalRevenue(),
            "pendingAmount", repo.pendingAmount()
        );
    }

    public String addBill(Billing b) {
        if (b.getPaymentStatus() == null || b.getPaymentStatus().isBlank()) b.setPaymentStatus("Pending");
        return repo.save(b) > 0 ? "Bill created successfully" : "Failed to create bill";
    }

    public String updatePaymentStatus(int id, String status) {
        return repo.updateStatus(id, status) > 0 ? "Payment status updated to " + status : "Bill not found";
    }

    public String updateBill(Billing b) {
        return repo.update(b) > 0 ? "Bill updated successfully" : "Bill not found";
    }

    public String deleteBill(int id) {
        return repo.delete(id) > 0 ? "Bill deleted" : "Bill not found";
    }
}
