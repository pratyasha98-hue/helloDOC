package com.healthcare.repository;

import com.healthcare.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentRepository {

    private final JdbcTemplate jdbc;

    public AppointmentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Appointment> rowMapper = (rs, rowNum) -> {
        Appointment a = new Appointment();
        a.setAppointmentId(rs.getInt("appointment_id"));
        a.setPatientId(rs.getInt("patient_id"));
        a.setDoctorId(rs.getInt("doctor_id"));
        a.setAppointmentDate(rs.getString("appointment_date"));
        a.setAppointmentTime(rs.getString("appointment_time"));
        a.setStatus(rs.getString("status"));
        a.setTokenNumber(rs.getInt("token_number"));
        a.setEmergencyFlag(rs.getString("emergency_flag"));
        try { a.setPatientName(rs.getString("patient_name")); } catch (Exception ignored) {}
        try { a.setDoctorName(rs.getString("doctor_name"));   } catch (Exception ignored) {}
        return a;
    };

    private static final String JOIN_SQL = """
        SELECT a.*,
               p.name AS patient_name,
               d.name AS doctor_name
        FROM appointments a
        JOIN patients p ON a.patient_id = p.patient_id
        JOIN doctors  d ON a.doctor_id  = d.doctor_id
        """;

    public List<Appointment> findAll() {
        return jdbc.query(JOIN_SQL + " ORDER BY a.appointment_date DESC, a.token_number", rowMapper);
    }

    public Optional<Appointment> findById(int id) {
        List<Appointment> result = jdbc.query(JOIN_SQL + " WHERE a.appointment_id = ?", rowMapper, id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public List<Appointment> findByDate(String date) {
        return jdbc.query(JOIN_SQL + " WHERE a.appointment_date = ? ORDER BY a.token_number", rowMapper, date);
    }

    public List<Appointment> findByPatient(int patientId) {
        return jdbc.query(JOIN_SQL + " WHERE a.patient_id = ? ORDER BY a.appointment_date DESC", rowMapper, patientId);
    }

    public List<Appointment> findByDoctor(int doctorId) {
        return jdbc.query(JOIN_SQL + " WHERE a.doctor_id = ? ORDER BY a.appointment_date DESC", rowMapper, doctorId);
    }

    public List<Appointment> findEmergencies() {
        return jdbc.query(JOIN_SQL + " WHERE a.emergency_flag = 'Yes' ORDER BY a.appointment_date DESC", rowMapper);
    }

    public List<Appointment> findByStatus(String status) {
        return jdbc.query(JOIN_SQL + " WHERE a.status = ? ORDER BY a.appointment_date DESC", rowMapper, status);
    }

    // Next token number for a given doctor on a given date
    public int nextToken(int doctorId, String date) {
        Integer max = jdbc.queryForObject(
            "SELECT MAX(token_number) FROM appointments WHERE doctor_id = ? AND appointment_date = ?",
            Integer.class, doctorId, date);
        return (max == null) ? 1 : max + 1;
    }

    public int save(Appointment a) {
        int token = nextToken(a.getDoctorId(), a.getAppointmentDate());
        return jdbc.update(
            "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status, token_number, emergency_flag) VALUES (?,?,?,?,?,?,?)",
            a.getPatientId(), a.getDoctorId(), a.getAppointmentDate(),
            a.getAppointmentTime(), a.getStatus(), token, a.getEmergencyFlag());
    }

    public int updateStatus(int id, String status) {
        return jdbc.update("UPDATE appointments SET status = ? WHERE appointment_id = ?", status, id);
    }

    public int update(Appointment a) {
        return jdbc.update(
            "UPDATE appointments SET patient_id=?, doctor_id=?, appointment_date=?, appointment_time=?, status=?, emergency_flag=? WHERE appointment_id=?",
            a.getPatientId(), a.getDoctorId(), a.getAppointmentDate(),
            a.getAppointmentTime(), a.getStatus(), a.getEmergencyFlag(), a.getAppointmentId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM appointments WHERE appointment_id = ?", id);
    }

    // Report: count by status
    public List<java.util.Map<String, Object>> countByStatus() {
        return jdbc.queryForList(
            "SELECT status, COUNT(*) AS count FROM appointments GROUP BY status");
    }

    // Report: doctor workload
    public List<java.util.Map<String, Object>> doctorWorkload() {
        return jdbc.queryForList("""
            SELECT d.name AS doctor_name, COUNT(a.appointment_id) AS total_appointments
            FROM doctors d
            LEFT JOIN appointments a ON d.doctor_id = a.doctor_id
            GROUP BY d.doctor_id, d.name
            ORDER BY total_appointments DESC
            """);
    }
}
