package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum SpinningTechType {

  COMPACT(0, "COMPACT", "CM"),
  RINGYARN(1, "RINGYARN", "RY"),
  OPENEND(2, "OPENEND", "OE"),
  AIRJET(3, "AIRJET", "AJ");

  private int type;
  private String name;
  private String code;

  private static final Map<Integer, SpinningTechType> BY_LABEL = new HashMap<>();

  static {
    for (SpinningTechType e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static SpinningTechType valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  SpinningTechType(int type, String name, String code) {
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
