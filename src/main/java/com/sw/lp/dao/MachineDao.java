package com.sw.lp.dao;

import com.sw.lp.entity.Machine;
import com.sw.lp.mappers.MachineMapper;
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

  public void saveMachine(Machine machine) {
    String sql = "INSERT INTO machine (mill_id, name, model, make, year, machine_type) values (:mill_id, :name, "
        + ":model, :make, :year, :machine_type)";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("mill_id", machine.getMillId());
    parameters.put("name", machine.getName());
    parameters.put("model", machine.getModel().getType());
    parameters.put("make", machine.getMake().getType());
    parameters.put("year", machine.getYear());
    parameters.put("machine_type", machine.getType().getType());
    namedJdbcTemplate.update(sql, parameters);
  }

  public List<Machine> getAllMachines(int millId) {
    String sql = "SELECT * FROM machine where machine.mill_id=?";
    return jdbcTemplate.query(sql, new MachineMapper(), millId);
  }

}
