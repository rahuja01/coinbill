package com.bill.coinbill.repository;


import com.bill.coinbill.entity.CoinSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<CoinSchema, Long> {


    @Query(value = "select * from COIN_TABLE", nativeQuery = true)
    List<CoinSchema> getCoinDetails();

}
