package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum EnduseType {

  KNITTING(0, "KNITTING", "KN"),
  WEAVING(1, "WEAVING", "WV"),
  INDUSTRIAL(2, "INDUSTRIAL", "IN");

  private int type;
  private String name;
  private String code;

  private static final Map<Integer, EnduseType> BY_LABEL = new HashMap<>();

  static {
    for (EnduseType e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static EnduseType valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  EnduseType(int type, String name, String code) {
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
