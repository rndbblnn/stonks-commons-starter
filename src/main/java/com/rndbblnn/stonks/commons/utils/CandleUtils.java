package com.rndbblnn.stonks.commons.utils;

import com.google.common.collect.Lists;
import com.rndbblnn.stonks.commons.dto.CandleDto;
import com.rndbblnn.stonks.commons.dto.TimeframeEnum;
import com.rndbblnn.stonks.commons.entity.BaseCandle;
import com.rndbblnn.stonks.commons.entity.Candle1mEntity;
import com.rndbblnn.stonks.commons.entity.CandleDailyEntity;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

public class CandleUtils {


  public static final List<CandleDto> resample(List<? extends BaseCandle> entityList, TimeframeEnum timeframe) {

    if (CollectionUtils.isEmpty(entityList)) {
      return Collections.emptyList();
    }

    // intra
    int resampleSize = 0;
    switch (timeframe) {
      case tf_3m:
        resampleSize = 3;
        break;
      case tf_5m:
        resampleSize = 5;
        break;
      case tf_15m:
        resampleSize = 15;
        break;
      case tf_30m:
        resampleSize = 30;
        break;
      case tf_1h:
        resampleSize = 60;
        break;
      case tf_4h:
      case tf_d:
      case tf_4w:
        throw new UnsupportedOperationException(timeframe + " not supported");
    }

    if (resampleSize > 0) {
      if (!(entityList.iterator().next() instanceof Candle1mEntity)) {
        throw new RuntimeException("Cannot resample intraday candles with non-1m candles");
      }

      return Lists.partition(entityList, resampleSize)
          .stream()
          .map(list -> new CandleDto()
              .setOpen(list.get(0).getOpen())
              .setHigh(list.stream().mapToDouble(BaseCandle::getHigh).max().orElse(0))
              .setLow(list.stream().mapToDouble(BaseCandle::getLow).min().orElse(0))
              .setClose(list.get(list.size() - 1).getClose())
              .setVolume(list.stream().mapToLong(BaseCandle::getVolume).sum())
              .setTickTime(list.get(0).getTickTime()))
          .collect(Collectors.toList());
    }

    // weekly
    if (!(entityList.iterator().next() instanceof CandleDailyEntity)) {
      throw new RuntimeException("Cannot resample weekly candles with non-daily candles");
    }

    List<CandleDto> candleDtoList = new ArrayList<>();

    CandleDto candleDto = null;
    CandleDto prevCandleDto = null;

    for (BaseCandle entity : entityList) {


      boolean firstDay = DayOfWeek.MONDAY.equals(entity.getTickTime().getDayOfWeek());
      if (firstDay) {
        candleDto = new CandleDto()
            .setOpen(entity.getOpen())
            .setHigh(entity.getHigh())
            .setLow(entity.getLow())
            .setClose(entity.getClose())
            .setVolume(entity.getVolume())
            .setTickTime(entity.getTickTime());
        continue;
      }

      prevCandleDto = candleDto;
      candleDto
          .setHigh(Math.max(entity.getHigh(), candleDto.getHigh()))
          .setLow(Math.min(entity.getLow(), candleDto.getLow()))
          .setVolume(candleDto.getVolume() + entity.getVolume());


      if (DayOfWeek.FRIDAY.equals(entity.getTickTime().getDayOfWeek())) {
        prevCandleDto = null;
      }
    }

    throw new RuntimeException("oopsie");
  }

  public static final CandleDto map(BaseCandle baseCandle) {
    return new CandleDto()
        .setOpen(baseCandle.getOpen())
        .setHigh(baseCandle.getHigh())
        .setLow(baseCandle.getLow())
        .setClose(baseCandle.getClose())
        .setVolume(baseCandle.getVolume())
        .setTickTime(baseCandle.getTickTime());
  }
}
