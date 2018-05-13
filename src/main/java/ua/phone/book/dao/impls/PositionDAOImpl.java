package ua.phone.book.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ua.phone.book.dao.interfaces.PositionDAO;
import ua.phone.book.models.Position;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PositionDAOImpl implements PositionDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Position> getAllPositions() {
        String sql = "SELECT * FROM positions";
        try {
            return jdbcTemplate.query(sql, new PositionMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Position getPositionById(int id) {
        String sql = "SELECT * FROM positions WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, params, new PositionMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int insertPosition(Position position) {
        String sql = "INSERT INTO positions (name, search_field) VALUES (:name, :searchField)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", position.getName());
        params.addValue("searchField", position.getName().toUpperCase());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public void updatePosition(Position position) {
        String sql = "UPDATE positions SET name=:name, search_field=:request WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", position.getName());
        params.addValue("id", position.getId());
        params.addValue("request", position.getName().toUpperCase());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deletePosition(int id) {
        String sql = "DELETE FROM positions WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    private static final class PositionMapper implements RowMapper<Position> {

        @Override
        public Position mapRow(ResultSet rs, int i) throws SQLException {
            Position position = new Position();
            position.setId(rs.getInt("id"));
            position.setName(rs.getString("name"));
            return position;
        }
    }
}
