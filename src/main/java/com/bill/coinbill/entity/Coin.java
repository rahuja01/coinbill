package com.bill.coinbill.entity;

import com.google.gson.Gson;

import javax.persistence.*;

@Entity
@Table(name = "COIN")
public class Coin {

    @Id
    @Column(name = "COIN_ID")
    private long id;
    @Column(name = "COIN_TYPE")
    private String coinType;
    @Column(name = "COIN_COUNT")
    private long coinCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public long getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(long coinCount) {
        this.coinCount = coinCount;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}