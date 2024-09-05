package com.sw.lp.service;

import com.sw.lp.dao.MachineDao;
import com.sw.lp.record.Machine;
import java.util.List;
import org.springframework.stereotype.Service;

@Service("machineService")
public class MachineService {

  private MachineDao machineDao;

  public MachineService(MachineDao machineDao) {
    this.machineDao = machineDao;
  }

  public void saveMachine(Machine machine, String millId) {
    machineDao.saveMachine(machine, millId);
  }

  public List<Machine> getAllMachines(String millId) {
    return machineDao.getAllMachines(millId);
  }

}
