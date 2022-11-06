package com.rndbblnn.stonks.commons.dto;

public enum SecurityTypeEnum {
  US_STOCK(1),
  CRYPTO_BINANCE(2);

  private final int value;

  SecurityTypeEnum(int value) {
    this.value = value;
  }
}
