package com.sw.lp.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class LpUtils {

  public static String generateBatchId() {
    return RandomStringUtils.randomAlphanumeric(10).toLowerCase();
  }

}
