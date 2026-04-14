package com.healthcare.repository;

import com.healthcare.model.Prescription;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PrescriptionRepository {

    private final JdbcTemplate jdbc;

    public PrescriptionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Prescription> rowMapper = (rs, rowNum) -> {
        Prescription p = new Prescription();
        p.setPrescriptionId(rs.getInt("prescription_id"));
        p.setAppointmentId(rs.getInt("appointment_id"));
        p.setMedicineName(rs.getString("medicine_name"));
        p.setDosage(rs.getString("dosage"));
        p.setNotes(rs.getString("notes"));
        return p;
    };

    public List<Prescription> findAll() {
        return jdbc.query("SELECT * FROM prescriptions ORDER BY prescription_id", rowMapper);
    }

    public Optional<Prescription> findById(int id) {
        List<Prescription> result = jdbc.query(
            "SELECT * FROM prescriptions WHERE prescription_id = ?", rowMapper, id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public List<Prescription> findByAppointment(int appointmentId) {
        return jdbc.query(
            "SELECT * FROM prescriptions WHERE appointment_id = ?", rowMapper, appointmentId);
    }

    public int save(Prescription p) {
        return jdbc.update(
            "INSERT INTO prescriptions (appointment_id, medicine_name, dosage, notes) VALUES (?,?,?,?)",
            p.getAppointmentId(), p.getMedicineName(), p.getDosage(), p.getNotes());
    }

    public int update(Prescription p) {
        return jdbc.update(
            "UPDATE prescriptions SET appointment_id=?, medicine_name=?, dosage=?, notes=? WHERE prescription_id=?",
            p.getAppointmentId(), p.getMedicineName(), p.getDosage(), p.getNotes(), p.getPrescriptionId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM prescriptions WHERE prescription_id = ?", id);
    }
}
