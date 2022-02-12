package com.bill.coinbill.repository;


import com.bill.coinbill.entity.CoinSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<CoinSchema, Long> {


    //List<CoinSchema> getCoinDetails();

}
