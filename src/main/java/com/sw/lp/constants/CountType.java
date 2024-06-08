package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum CountType {
  C16(0, "C16", "016"),
  C18(1, "C18", "018"),
  C20(2, "C20", "020"),
  C24(3, "C24", "024"),
  C26(4, "C26", "026");

  private int type;
  private String name;
  private String code;

  private static final Map<Integer, CountType> BY_LABEL = new HashMap<>();

  static {
    for (CountType e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static CountType valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  CountType(int type, String name, String code) {
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
