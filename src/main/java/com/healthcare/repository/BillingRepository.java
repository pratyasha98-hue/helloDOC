package com.healthcare.repository;

import com.healthcare.model.Billing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BillingRepository {

    private final JdbcTemplate jdbc;

    public BillingRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Billing> rowMapper = (rs, rowNum) -> {
        Billing b = new Billing();
        b.setBillId(rs.getInt("bill_id"));
        b.setAppointmentId(rs.getInt("appointment_id"));
        b.setAmount(rs.getDouble("amount"));
        b.setPaymentStatus(rs.getString("payment_status"));
        try { b.setPatientName(rs.getString("patient_name"));       } catch (Exception ignored) {}
        try { b.setDoctorName(rs.getString("doctor_name"));         } catch (Exception ignored) {}
        try { b.setAppointmentDate(rs.getString("appointment_date")); } catch (Exception ignored) {}
        return b;
    };

    private static final String JOIN_SQL = """
        SELECT b.*,
               p.name AS patient_name,
               d.name AS doctor_name,
               a.appointment_date
        FROM billing b
        JOIN appointments a ON b.appointment_id = a.appointment_id
        JOIN patients     p ON a.patient_id      = p.patient_id
        JOIN doctors      d ON a.doctor_id        = d.doctor_id
        """;

    public List<Billing> findAll() {
        return jdbc.query(JOIN_SQL + " ORDER BY b.bill_id DESC", rowMapper);
    }

    public Optional<Billing> findById(int id) {
        List<Billing> result = jdbc.query(JOIN_SQL + " WHERE b.bill_id = ?", rowMapper, id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public List<Billing> findByStatus(String status) {
        return jdbc.query(JOIN_SQL + " WHERE b.payment_status = ?", rowMapper, status);
    }

    public int save(Billing b) {
        return jdbc.update(
            "INSERT INTO billing (appointment_id, amount, payment_status) VALUES (?,?,?)",
            b.getAppointmentId(), b.getAmount(), b.getPaymentStatus());
    }

    public int updateStatus(int id, String status) {
        return jdbc.update("UPDATE billing SET payment_status = ? WHERE bill_id = ?", status, id);
    }

    public int update(Billing b) {
        return jdbc.update(
            "UPDATE billing SET appointment_id=?, amount=?, payment_status=? WHERE bill_id=?",
            b.getAppointmentId(), b.getAmount(), b.getPaymentStatus(), b.getBillId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM billing WHERE bill_id = ?", id);
    }

    // Total revenue
    public double totalRevenue() {
        Double total = jdbc.queryForObject(
            "SELECT SUM(amount) FROM billing WHERE payment_status = 'Paid'", Double.class);
        return total == null ? 0 : total;
    }

    public double pendingAmount() {
        Double total = jdbc.queryForObject(
            "SELECT SUM(amount) FROM billing WHERE payment_status = 'Pending'", Double.class);
        return total == null ? 0 : total;
    }
}
