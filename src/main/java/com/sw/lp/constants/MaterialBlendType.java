package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum MaterialBlendType {
  SINGLE100(0, "100", "01"),
  DOUBLE65_35(1, "65/35", "02"),
  DOUBLE52_48(2, "52/48", "03");

  private int type;
  private String name;
  private String code;

  private static final Map<Integer, MaterialBlendType> BY_LABEL = new HashMap<>();

  static {
    for (MaterialBlendType e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static MaterialBlendType valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  MaterialBlendType(int type, String name, String code) {
    this.type = type;
    this.name = name;
    this.code = code;
  }

  public int getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }
}
