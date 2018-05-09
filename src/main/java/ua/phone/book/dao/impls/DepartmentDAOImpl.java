package ua.phone.book.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.phone.book.dao.interfaces.DepartmentDAO;
import ua.phone.book.models.Department;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DepartmentDAOImpl implements DepartmentDAO {


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Department> getAllDepartments() {
        String sql = "SELECT * FROM departments";
        try {
            return jdbcTemplate.query(sql, new DepartmentMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Department getDepartmentById(int id) {
        String sql = "SELECT * FROM departments WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new DepartmentMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int insertDepartment(Department department) {
        String sql = "INSERT INTO departments (name, search_field) VALUES (:name, :searchField)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", department.getName());
        params.addValue("searchField", department.getName().toUpperCase());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public void updateDepartment(Department department) {
        String sql = "UPDATE departments SET name=:name, search_field=:searchField WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", department.getId());
        params.addValue("name", department.getName());
        params.addValue("searchField", department.getName().toUpperCase());
        jdbcTemplate.update(sql, params);
    }



    @Override
    public void deleteDepartment(int id) {
        String sql = "DELETE FROM departments WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    private static final class DepartmentMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet rs, int i) throws SQLException {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setName(rs.getString("name"));
            return department;
        }
    }
}
