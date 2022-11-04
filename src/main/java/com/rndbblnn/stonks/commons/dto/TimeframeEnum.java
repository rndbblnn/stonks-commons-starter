package com.rndbblnn.stonks.commons.dto;

public enum TimeframeEnum {
  tf_1m, tf_3m, tf_5m, tf_15m, tf_30m, tf_1h, tf_4h, tf_d, tf_w, tf_4w;

  public String toTimeframeStr() {
    return this.name().replaceAll("tf\\_", "");
  }

  public static TimeframeEnum fromTimeframeStr(String timeframeStr) {
    try {
      return TimeframeEnum.valueOf("tf_" + timeframeStr);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
