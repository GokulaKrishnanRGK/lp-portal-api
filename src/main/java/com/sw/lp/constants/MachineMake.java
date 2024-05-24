package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum MachineMake {

  MAKE1(0, "MAKE1"),
  MAKE2(1, "MAKE2");

  private int type;
  private String name;

  private static final Map<Integer, MachineMake> BY_LABEL = new HashMap<>();

  static {
    for (MachineMake e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static MachineMake valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  MachineMake(int type, String name) {
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
