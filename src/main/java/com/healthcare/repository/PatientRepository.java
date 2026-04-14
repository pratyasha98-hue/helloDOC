package com.healthcare.repository;

import com.healthcare.model.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepository {

    private final JdbcTemplate jdbc;

    public PatientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Patient> rowMapper = (rs, rowNum) -> {
        Patient p = new Patient();
        p.setPatientId(rs.getInt("patient_id"));
        p.setName(rs.getString("name"));
        p.setAge(rs.getInt("age"));
        p.setGender(rs.getString("gender"));
        p.setPhone(rs.getString("phone"));
        p.setCity(rs.getString("city"));
        p.setMedicalHistory(rs.getString("medical_history"));
        return p;
    };

    public List<Patient> findAll() {
        return jdbc.query("SELECT * FROM patients ORDER BY patient_id", rowMapper);
    }

    public Optional<Patient> findById(int id) {
        List<Patient> result = jdbc.query(
            "SELECT * FROM patients WHERE patient_id = ?", rowMapper, id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    // Search by name or city
    public List<Patient> search(String keyword) {
        String q = "%" + keyword + "%";
        return jdbc.query(
            "SELECT * FROM patients WHERE name LIKE ? OR city LIKE ? OR medical_history LIKE ?",
            rowMapper, q, q, q);
    }

    public int save(Patient p) {
        return jdbc.update(
            "INSERT INTO patients (name, age, gender, phone, city, medical_history) VALUES (?,?,?,?,?,?)",
            p.getName(), p.getAge(), p.getGender(), p.getPhone(), p.getCity(), p.getMedicalHistory());
    }

    public int update(Patient p) {
        return jdbc.update(
            "UPDATE patients SET name=?, age=?, gender=?, phone=?, city=?, medical_history=? WHERE patient_id=?",
            p.getName(), p.getAge(), p.getGender(), p.getPhone(), p.getCity(), p.getMedicalHistory(), p.getPatientId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM patients WHERE patient_id = ?", id);
    }
}
