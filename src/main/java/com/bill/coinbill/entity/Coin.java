package com.bill.coinbill.entity;

import com.google.gson.Gson;

import javax.persistence.*;

@Entity
@Table(name = "COIN")
public class Coin {

    @Id
    @Column(name = "COIN_ID")
    private long coinId;

    @Column(name = "COIN_VALUE")
    private int coinCount;

    @Column(name = "COIN_TYPE")
    String coinTypeEnum;

    public long getCoinId() {
        return coinId;
    }

    public void setCoinId(long coinId) {
        this.coinId = coinId;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public String getCoinTypeEnum() {
        return coinTypeEnum;
    }

    public void setCoinTypeEnum(String coinTypeEnum) {
        this.coinTypeEnum = coinTypeEnum;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
