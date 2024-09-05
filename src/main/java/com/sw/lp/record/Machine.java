package com.sw.lp.record;

import com.sw.lp.constants.MachineMake;
import com.sw.lp.constants.MachineModel;
import com.sw.lp.constants.MachineType;

public record Machine(String machineId, String millId, String name, MachineMake make, MachineModel model, int year, MachineType type) {

  public Machine {
  }
}
