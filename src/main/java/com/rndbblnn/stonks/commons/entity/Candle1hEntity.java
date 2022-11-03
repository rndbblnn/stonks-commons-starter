package com.rndbblnn.stonks.commons.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "candle_1h")
@ToString
public class Candle1hEntity extends BaseCandle {

}
