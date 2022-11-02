package com.rndbblnn.stonks.commons.dto;

import lombok.Data;

@Data
public class CandleDto {

  private String symbol;
  private Double open;
  private Double high;
  private Double low;
  private Double close;
  private Long volume;

}
