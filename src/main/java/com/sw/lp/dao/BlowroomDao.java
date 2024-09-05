package com.sw.lp.dao;

import com.sw.lp.record.BlowroomLine;
import com.sw.lp.record.MachinePositionMapping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("blowroomDao")
public class BlowroomDao {

  private static final Logger logger = LoggerFactory.getLogger(BlowroomDao.class);

  private JdbcTemplate jdbcTemplate;
  private NamedParameterJdbcTemplate namedJdbcTemplate;

  public BlowroomDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedJdbcTemplate = namedJdbcTemplate;
  }

  public void newBlowroomLine(BlowroomLine blowroomLine, String millId) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("mill_id", millId);
    parameters.put("unit_id", blowroomLine.unitId());
    parameters.put("name", blowroomLine.name());
    parameters.put("description", blowroomLine.description());
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    KeyHolder keyHolder = simpleJdbcInsert.withTableName("blowroom_line")
        .usingColumns("mill_id", "unit_id", "name", "description")
        .usingGeneratedKeyColumns("blowroom_line_id")
        .executeAndReturnKeyHolder(parameters);

    String blowroomLineId = keyHolder.getKeyAs(UUID.class).toString();
    createMachinePositionMappings(blowroomLine.machinePositionMappings(), blowroomLineId);
  }

  public void createMachinePositionMappings(List<MachinePositionMapping> machinePositionMappings, String blowroomLineId) {
    List<Map<String, Object>> machineBatchValues = new ArrayList<>();
    machinePositionMappings.forEach((mpm) -> {
      Map<String, Object> machineBatch = new HashMap<>();
      machineBatch.put("blowroom_line_id", blowroomLineId);
      machineBatch.put("machine_id", mpm.machineId());
      machineBatch.put("position", mpm.position());
    });
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    simpleJdbcInsert.withTableName("blowroom_line_machine_mapping")
        .usingColumns("blowroom_line_id", "machine_id", "position")
        .executeBatch(machineBatchValues.toArray(new Map[machinePositionMappings.size()]));
  }

}
