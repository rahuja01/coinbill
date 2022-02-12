package com.bill.coinbill.repository;


import com.bill.coinbill.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {


    @Query(value = "select * from COIN", nativeQuery = true)
    List<Coin> getCoinDetails();

    /*@Modifying
    @Query(value = "update COIN set COIN_COUNT = ?1 where COIN_TYPE = 'Q'", nativeQuery = true)
    int updateCoinBalance(@Nullable long coinCount, @NonNull String coinType);*/


}
