package ua.phone.book.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.phone.book.dao.interfaces.EmployeeDAO;
import ua.phone.book.models.Employee;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class EmployeeDAOImpl implements EmployeeDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        String sql = "SELECT * FROM employee WHERE department_id=:departmentId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("departmentId", departmentId);
        try {
            return jdbcTemplate.query(sql, params, new EmloyeeMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int getEmployeeCountByPositionId(int id) {
        String sql = "SELECT count(*) FROM employee WHERE position_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    @Override
    public void insertEmployee(Employee employee) {
        String sql = "INSERT INTO employee (full_name, position_id, ledline_number, mobile_number, department_Id, search_field)" +
                "VALUES (:fullName, :positionId, :ledlineNum, :mobileNum, :departmentId, :searchField)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fullName", employee.getFullName());
        params.addValue("positionId", employee.getPositionId());
        params.addValue("ledlineNum", employee.getLedlineNumber());
        params.addValue("mobileNum", employee.getMobileNumber());
        params.addValue("departmentId", employee.getDepartmentId());
        params.addValue("searchField", employee.getFullName().toUpperCase());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET full_name=:fullName, position_id=:positionId, " +
                "ledline_number=:ledlineNum, mobile_number=:mobileNum, department_Id=:departmentId, search_field=:searchField" +
                " WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", employee.getId());
        params.addValue("fullName", employee.getFullName());
        params.addValue("positionId", employee.getPositionId());
        params.addValue("ledlineNum", employee.getLedlineNumber());
        params.addValue("mobileNum", employee.getMobileNumber());
        params.addValue("departmentId", employee.getDepartmentId());
        params.addValue("searchField", employee.getFullName().toUpperCase());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Employee> searchEmployee(String request) {
        String sql = "SELECT * FROM employee e, departments d, positions p" +
                " WHERE e.department_id=d.id and e.position_id=p.id and" +
                " (e.search_field LIKE :request " +
                "or e.ledline_number LIKE :request " +
                "or e.mobile_number LIKE :request " +
                "or d.search_field LIKE :request " +
                "or p.search_field LIKE :request)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("request","%" + request.toUpperCase() + "%");
        try {
            return jdbcTemplate.query(sql, params, new EmloyeeMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int getEmployeeCountByDepartmentId(int id) {
        String sql = "SELECT COUNT(*) FROM employee WHERE department_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    private static final class EmloyeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFullName(rs.getString("full_name"));
            employee.setPositionId(rs.getInt("position_id"));
            employee.setLedlineNumber(rs.getString("ledline_number"));
            employee.setMobileNumber(rs.getString("mobile_number"));
            employee.setDepartmentId(rs.getInt("department_id"));
            return employee;
        }
    }
}
