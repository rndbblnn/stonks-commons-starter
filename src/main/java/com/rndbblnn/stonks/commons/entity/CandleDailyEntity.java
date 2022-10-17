package com.rndbblnn.stonks.commons.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "candle_d")
@ToString
public class CandleDailyEntity extends BaseCandle {

}
