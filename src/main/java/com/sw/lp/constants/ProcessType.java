package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum ProcessType {

  CARDED(0, "CARDED", "CD"),
  COMBED(1, "COMBED", "CM"),
  SEMICOMBED(2, "SEMICOMBED", "SCM");

  private int type;
  private String name;
  private String code;

  private static final Map<Integer, ProcessType> BY_LABEL = new HashMap<>();

  static {
    for (ProcessType e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static ProcessType valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  ProcessType(int type, String name, String code) {
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
