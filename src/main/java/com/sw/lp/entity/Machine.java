package com.sw.lp.entity;

import com.sw.lp.constants.MachineMake;
import com.sw.lp.constants.MachineModel;
import com.sw.lp.constants.MachineType;

public class Machine {

  private int machineId;
  private int millId;
  private String name;
  private String description;
  private MachineMake make;
  private MachineModel model;
  private int year;
  private MachineType type;

  public int getMachineId() {
    return machineId;
  }

  public void setMachineId(int machineId) {
    this.machineId = machineId;
  }

  public int getMillId() {
    return millId;
  }

  public void setMillId(int millId) {
    this.millId = millId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public MachineMake getMake() {
    return make;
  }

  public void setMake(MachineMake make) {
    this.make = make;
  }

  public MachineModel getModel() {
    return model;
  }

  public void setModel(MachineModel model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public MachineType getType() {
    return type;
  }

  public void setType(MachineType type) {
    this.type = type;
  }
}
