package com.bill.coinbill.repository;


import com.bill.coinbill.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Integer> {


    @Query(value = "select * from COIN", nativeQuery = true)
    List<Coin> getCoinDetails();

}
