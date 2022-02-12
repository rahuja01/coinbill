package com.bill.coinbill.service;

import com.bill.coinbill.entity.CoinSchema;
import com.bill.coinbill.entity.CoinType;
import com.bill.coinbill.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoinService {

    @Autowired
    CoinRepository coinRepository;

    /*@Autowired
    List<CoinSchema> coinSchemasList;*/

    public List<CoinSchema> getCoin(Integer bill){

        // logic for coin bill dispenser

        List<CoinSchema> coinSchemasList = new ArrayList<>();

        List<CoinSchema> coinCountLstFromDB = coinRepository.findAll();

        System.out.println("coinCountLstFromDB size: " + coinCountLstFromDB.size());

        for(CoinSchema coinSchema :  coinCountLstFromDB){
            System.out.println("coinSchema Type: " + coinSchema.getCoinTypeEnum());
            System.out.println("coinSchema Value: " + coinSchema.getCoinCount());
        }

        int totalCoins = 0;
        int cents = (int) Math.round(100*bill);
        int quarters = Math.round((int)cents/25);
        totalCoins +=quarters;
        cents=cents%25;
        int dimes = Math.round((int)cents/10);
        totalCoins +=dimes;
        cents=cents%10;
        int nickels = Math.round((int)cents/5);
        totalCoins +=nickels;
        cents=cents%5;
        int pennies = Math.round((int)cents/1);
        totalCoins +=pennies;

        System.out.println("Dollars: " + cents);
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);
        System.out.println("totalCoins: " + totalCoins);

        CoinSchema coinSchema = new CoinSchema();
        coinSchema.setCoinCount(totalCoins);
        coinSchema.setCoinTypeEnum(CoinType.Q);

        coinSchemasList.add(coinSchema);



        return coinSchemasList;
    }

}
