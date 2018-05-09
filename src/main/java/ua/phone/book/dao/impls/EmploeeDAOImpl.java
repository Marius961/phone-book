package ua.phone.book.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.phone.book.dao.interfaces.EmploeeDAO;
import ua.phone.book.models.Emploee;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class EmploeeDAOImpl implements EmploeeDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Emploee> getEmploeesByDepartamentId(int departmentId) {
        String sql = "SELECT * FROM emploee WHERE department_id=:departmentId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("departmentId", departmentId);
        try {
            return jdbcTemplate.query(sql, params, new EmloeeMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Emploee getEmploeeById(int id) {
        String sql = "SELECT * FROM emploee WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new EmloeeMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertEmploee(Emploee emploee) {
        String sql = "INSERT INTO emploee (full_name, position_id, ledline_number, mobile_number, department_Id, search_field)" +
                "VALUES (:fullName, :positionId, :ledlineNum, :mobileNum, :departmentId, :searchField)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fullName", emploee.getFullName());
        params.addValue("positionId", emploee.getPositionId());
        params.addValue("ledlineNum", emploee.getLedlineNumber());
        params.addValue("mobileNum", emploee.getMobileNumber());
        params.addValue("departmentId", emploee.getDepartmentId());
        params.addValue("searchField", emploee.getFullName().toUpperCase());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateEmploee(Emploee emploee) {
        String sql = "UPDATE emploee SET full_name=:fullName, position_id=:positionId, " +
                "ledline_number=:ledlineNum, mobile_number=:mobileNum, department_Id=:departmentId, search_field=:searchField" +
                " WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", emploee.getId());
        params.addValue("fullName", emploee.getFullName());
        params.addValue("positionId", emploee.getPositionId());
        params.addValue("ledlineNum", emploee.getLedlineNumber());
        params.addValue("mobileNum", emploee.getMobileNumber());
        params.addValue("departmentId", emploee.getDepartmentId());
        params.addValue("searchField", emploee.getFullName().toUpperCase());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteEmploee(int id) {
        String sql = "DELETE FROM emploee WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Emploee> searchEmploee(String request) {
        String sql = "SELECT * FROM emploee e, departments d, positions p" +
                " WHERE e.department_id=d.id and e.position_id=p.id and" +
                " (e.search_field LIKE :request " +
                "or e.ledline_number LIKE :request " +
                "or e.mobile_number LIKE :request " +
                "or d.search_field LIKE :request " +
                "or p.search_field LIKE :request)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("request","%" + request.toUpperCase() + "%");
        try {
            return jdbcTemplate.query(sql, params, new EmloeeMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int getEmploeeCoutByDepartmentId(int id) {
        String sql = "SELECT COUNT(*) FROM emploee WHERE department_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    private static final class EmloeeMapper implements RowMapper<Emploee> {

        @Override
        public Emploee mapRow(ResultSet rs, int i) throws SQLException {
            Emploee emploee = new Emploee();
            emploee.setId(rs.getInt("id"));
            emploee.setFullName(rs.getString("full_name"));
            emploee.setPositionId(rs.getInt("position_id"));
            emploee.setLedlineNumber(rs.getString("ledline_number"));
            emploee.setMobileNumber(rs.getString("mobile_number"));
            emploee.setDepartmentId(rs.getInt("department_id"));
            return emploee;
        }
    }
}
