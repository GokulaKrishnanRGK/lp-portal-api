package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum MaterialType {
  COTTON(0, "COTTON", "COT"),
  POLYESTER(1, "POLYESTER", "POL");

  private int type;
  private String name;
  private String code;

  private static final Map<Integer, MaterialType> BY_LABEL = new HashMap<>();

  static {
    for (MaterialType e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static MaterialType valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  MaterialType(int type, String name, String code) {
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
