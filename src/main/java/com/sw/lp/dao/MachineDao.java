package com.sw.lp.dao;

import com.sw.lp.record.Machine;
import com.sw.lp.dao.mappers.MachineMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("machineDao")
public class MachineDao {

  static final Logger logger = LoggerFactory.getLogger(MachineDao.class);

  private JdbcTemplate jdbcTemplate;
  private NamedParameterJdbcTemplate namedJdbcTemplate;

  public MachineDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedJdbcTemplate = namedJdbcTemplate;
  }

  public void saveMachine(Machine machine, int millId) {
    String sql = "INSERT INTO machine (mill_id, name, model, make, year, machine_type) values (:mill_id, :name, "
        + ":model, :make, :year, :machine_type)";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("mill_id", millId);
    parameters.put("name", machine.name());
    parameters.put("model", machine.model().getType());
    parameters.put("make", machine.make().getType());
    parameters.put("year", machine.year());
    parameters.put("machine_type", machine.type().getType());
    namedJdbcTemplate.update(sql, parameters);
  }

  public List<Machine> getAllMachines(int millId) {
    String sql = "SELECT * FROM machine where machine.mill_id=?";
    return jdbcTemplate.query(sql, new MachineMapper(), millId);
  }

}
