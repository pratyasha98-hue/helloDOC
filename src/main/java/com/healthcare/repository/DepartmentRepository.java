package com.healthcare.repository;

import com.healthcare.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository {

    private final JdbcTemplate jdbc;

    public DepartmentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Department> rowMapper = (rs, rowNum) -> {
        Department d = new Department();
        d.setDepartmentId(rs.getInt("department_id"));
        d.setDepartmentName(rs.getString("department_name"));
        return d;
    };

    public List<Department> findAll() {
        return jdbc.query("SELECT * FROM departments ORDER BY department_id", rowMapper);
    }

    public Optional<Department> findById(int id) {
        List<Department> result = jdbc.query(
            "SELECT * FROM departments WHERE department_id = ?", rowMapper, id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public int save(Department d) {
        return jdbc.update("INSERT INTO departments (department_name) VALUES (?)", d.getDepartmentName());
    }

    public int update(Department d) {
        return jdbc.update(
            "UPDATE departments SET department_name=? WHERE department_id=?",
            d.getDepartmentName(), d.getDepartmentId());
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM departments WHERE department_id = ?", id);
    }
}
