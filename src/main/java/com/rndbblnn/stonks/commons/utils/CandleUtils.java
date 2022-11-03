package com.rndbblnn.stonks.commons.utils;

import com.google.common.collect.Lists;
import com.rndbblnn.stonks.commons.dto.CandleDto;
import com.rndbblnn.stonks.commons.entity.BaseCandle;
import java.util.List;
import java.util.stream.Collectors;

public class CandleUtils {

  public static final List<CandleDto> resample(List<? extends BaseCandle> candleList, int resampleSize) {
    return Lists.partition(candleList, resampleSize)
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
