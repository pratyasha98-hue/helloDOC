package com.healthcare.repository;

import com.healthcare.model.Doctor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepository {

    private final JdbcTemplate jdbc;

    public DoctorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Doctor> rowMapper = (rs, rowNum) -> {
        Doctor d = new Doctor();
        d.setDoctorId(rs.getInt("doctor_id"));
        d.setName(rs.getString("name"));
        d.setSpecialization(rs.getString("specialization"));
        d.setDepartmentId(rs.getInt("department_id"));
        d.setConsultationFee(rs.getDouble("consultation_fee"));
        // May be null if not joined
        try { d.setDepartmentName(rs.getString("department_name")); } catch (Exception ignored) {}
        return d;
    };

    public List<Doctor> findAll() {
        String sql = """
            SELECT d.*, dep.department_name
            FROM doctors d
            JOIN departments dep ON d.department_id = dep.department_id
            ORDER BY d.doctor_id
            """;
        return jdbc.query(sql, rowMapper);
    }

    public Optional<Doctor> findById(int id) {
        String sql = """
            SELECT d.*, dep.department_name
            FROM doctors d
            JOIN departments dep ON d.department_id = dep.department_id
            WHERE d.doctor_id = ?
            """;
        List<Doctor> result = jdbc.query(sql, rowMapper, id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public List<Doctor> findByDepartment(int departmentId) {
        String sql = """
            SELECT d.*, dep.department_name
            FROM doctors d
            JOIN departments dep ON d.department_id = dep.department_id
            WHERE d.department_id = ?
            """;
        return jdbc.query(sql, rowMapper, departmentId);
    }

    public List<Doctor> search(String keyword) {
        String q = "%" + keyword + "%";
        String sql = """
            SELECT d.*, dep.department_name
            FROM doctors d
            JOIN departments dep ON d.department_id = dep.department_id
            WHERE d.name LIKE ? OR d.specialization LIKE ? OR dep.department_name LIKE ?
            """;
        return jdbc.query(sql, rowMapper, q, q, q);
    }

    public int save(Doctor d) {
        return jdbc.update(
            "INSERT INTO doctors (name, specialization, department_id, consultation_fee) VALUES (?,?,?,?)",
            d.getName(), d.getSpecialization(), d.getDepartmentId(), d.getConsultationFee());
    }

    public int update(Doctor d) {
        return jdbc.update(
            "UPDATE doctors SET name=?, specialization=?, department_id=?, consultation_fee=? WHERE doctor_id=?",
            d.getName(), d.getSpecialization(), d.getDepartmentId(), d.getConsultationFee(), d.getDoctorId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM doctors WHERE doctor_id = ?", id);
    }
}
