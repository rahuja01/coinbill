package com.bill.coinbill.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "COIN_TABLE")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoinSchema {

    @Id
    @Column(name = "COIN_ID")
    Long coinId;

    @Column(name = "COIN_VALUE")
    int coinCount;

    @Column(name = "COIN_TYPE")
    Enum<CoinType> coinTypeEnum;

}
