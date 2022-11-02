package com.rndbblnn.stonks.commons.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CandleDto {

  private String symbol;
  private Double open;
  private Double high;
  private Double low;
  private Double close;
  private Long volume;
  private LocalDateTime tickTime;

}
