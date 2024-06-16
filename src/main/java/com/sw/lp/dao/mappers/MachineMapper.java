package com.sw.lp.dao.mappers;

import com.sw.lp.constants.MachineMake;
import com.sw.lp.constants.MachineModel;
import com.sw.lp.constants.MachineType;
import com.sw.lp.record.Machine;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MachineMapper implements RowMapper<Machine> {

  @Override
  public Machine mapRow(ResultSet rs, int numRows) throws SQLException {
    String name = rs.getString("name");
    int millId = rs.getInt("mill_id");
    int year = rs.getInt("year");
    int machineId = rs.getInt("machine_id");
    MachineType type = MachineType.valueOfLabel(rs.getInt("machine_type"));
    MachineMake make = MachineMake.valueOfLabel(rs.getInt("make"));
    MachineModel model = MachineModel.valueOfLabel(rs.getInt("model"));
    return new Machine(machineId, millId, name, make, model, year, type);
  }
}
