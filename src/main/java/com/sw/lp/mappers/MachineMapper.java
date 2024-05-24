package com.sw.lp.mappers;

import com.sw.lp.constants.MachineMake;
import com.sw.lp.constants.MachineModel;
import com.sw.lp.constants.MachineType;
import com.sw.lp.entity.Machine;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MachineMapper implements RowMapper<Machine> {

  @Override
  public Machine mapRow(ResultSet rs, int numRows) throws SQLException {
    Machine machine = new Machine();
    machine.setName(rs.getString("name"));
    machine.setDescription(rs.getString("description"));
    machine.setMillId(rs.getInt("mill_id"));
    machine.setYear(rs.getInt("year"));
    machine.setMachineId(rs.getInt("machine_id"));
    MachineType type = MachineType.valueOfLabel(rs.getInt("machine_type"));
    MachineMake make = MachineMake.valueOfLabel(rs.getInt("make"));
    MachineModel model = MachineModel.valueOfLabel(rs.getInt("model"));
    machine.setType(type);
    machine.setModel(model);
    machine.setMake(make);
    return machine;
  }
}
