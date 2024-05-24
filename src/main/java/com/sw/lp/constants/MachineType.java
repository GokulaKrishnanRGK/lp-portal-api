package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum MachineType {

  BALEOPENER(0, "Bale-opener"),
  PRECLEANER(1, "Pre-cleaner"),
  MIXER(2, "Mixer"),
  FINECLEANER(3, "Fine-cleaner"),
  CARDING(4, "Carding");

  private static final Map<Integer, MachineType> BY_LABEL = new HashMap<>();

  static {
    for (MachineType e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static MachineType valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  private int type;
  private String name;

  MachineType(int type, String name) {
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
