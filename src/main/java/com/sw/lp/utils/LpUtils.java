package com.sw.lp.utils;

import com.sw.lp.record.Product;
import org.apache.commons.lang3.RandomStringUtils;

public class LpUtils {

  public static String generateBatchId() {
    return RandomStringUtils.randomAlphanumeric(10).toLowerCase();
  }

  public static String generateProductCode(Product product) {
    StringBuilder sb = new StringBuilder();
    sb.append(product.count().getCode());
    sb.append(product.materialType().getCode());
    sb.append(product.materialBlendType().getCode());
    sb.append(product.processType().getCode());
    sb.append(product.blendType().getCode());
    sb.append(product.spinningTechType().getCode());
    sb.append(product.enduseType().getCode());
    return sb.toString();
  }
}
