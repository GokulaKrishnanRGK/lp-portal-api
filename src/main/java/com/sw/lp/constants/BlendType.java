package com.sw.lp.constants;

import java.util.HashMap;
import java.util.Map;

public enum BlendType {
  BLOWROOM(0, "BLOWROOM", "CD"),
  DRAWFRAME(1, "DRAWFRAME", "CM");

  private int type;
  private String name;
  private String code;

  private static final Map<Integer, BlendType> BY_LABEL = new HashMap<>();

  static {
    for (BlendType e : values()) {
      BY_LABEL.put(e.type, e);
    }
  }

  public static BlendType valueOfLabel(int label) {
    return BY_LABEL.get(label);
  }

  BlendType(int type, String name, String code) {
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
