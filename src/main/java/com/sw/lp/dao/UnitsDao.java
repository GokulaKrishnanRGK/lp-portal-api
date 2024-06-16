package com.sw.lp.dao;

import com.sw.lp.record.Unit;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("unitsDao")
public class UnitsDao {

  private static final Logger logger = LoggerFactory.getLogger(UnitsDao.class);

  private JdbcTemplate jdbcTemplate;
  private NamedParameterJdbcTemplate namedJdbcTemplate;

  public UnitsDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedJdbcTemplate = namedJdbcTemplate;
  }

  public void saveUnits(List<Unit> units, int millId) {
    String sql = "INSERT INTO cotton_unit(name, mill_id) VALUES(?, ?)";
    jdbcTemplate.batchUpdate(sql, units, 50, (ps, unit) -> {
      ps.setString(1, unit.name());
      ps.setInt(2, millId);
    });
  }

  public List<Unit> getAllUnits(int millId) {
    String sql = "SELECT * FROM cotton_unit where mill_id=?";
    return jdbcTemplate.query(sql, (rs) -> {
      List<Unit> units = new ArrayList<>();
      while (rs.next()) {
        Unit unit = new Unit(rs.getInt("unit_id"), rs.getString("name"), rs.getInt("mill_id"));
        units.add(unit);
      }
      return units;
    }, millId);
  }

}
