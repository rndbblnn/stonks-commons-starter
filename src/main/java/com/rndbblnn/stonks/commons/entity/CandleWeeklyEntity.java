package com.rndbblnn.stonks.commons.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "candle_1w")
@ToString
public class CandleWeeklyEntity extends BaseCandle{

}
