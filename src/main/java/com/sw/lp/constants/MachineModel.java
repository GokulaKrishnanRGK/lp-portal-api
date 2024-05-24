package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum MachineModel {

  MODEL1(0, "MODEL1"),
  MODEL2(1, "MODEL2");

  private int type;
  private String name;

  private static final Map<Integer, MachineModel> BY_LABEL = new HashMap<>();

  static {
    for (MachineModel e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static MachineModel valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  MachineModel(int type, String name) {
    this.type = type;
    this.name = name;
  }

  public int getType() {
    return type;
  }

  public String getName() {
    return name;
  }

}
