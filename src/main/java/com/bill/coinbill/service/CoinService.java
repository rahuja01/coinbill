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

        int totalCoins = 0;
        int quarters =0;
        int dimes =0;
        int nickels=0;
        int pennies=0;
        int cents = (int) Math.round(100*bill);

        CoinSchema coinSchema = new CoinSchema();

        for(CoinSchema coinSchemaDB :  coinCountLstFromDB){
            System.out.println("coinSchema Type: " + coinSchema.getCoinTypeEnum());
            System.out.println("coinSchema Value: " + coinSchema.getCoinCount());

            if(coinSchemaDB.getCoinTypeEnum().equals(CoinType.Q)){
                quarters = Math.round((int)cents/25);
                if(coinSchemaDB.getCoinCount()>=quarters){

                    totalCoins +=quarters;
                    cents=cents%25;
                    coinSchemaDB.setCoinCount(coinSchemaDB.getCoinCount()-quarters);
                    //call service to update the count of quarters

                    return getCoinSchemas(coinSchema, totalCoins, CoinType.Q, coinSchemasList);

                }

            }

            if(coinSchemaDB.getCoinTypeEnum().equals(CoinType.D)){
                dimes = Math.round((int)cents/10);
                if(coinSchemaDB.getCoinCount()>=dimes){

                    totalCoins +=dimes;
                    cents=cents%10;
                    coinSchemaDB.setCoinCount(coinSchemaDB.getCoinCount()-dimes);
                    //call service to update the count of DIMES

                    return getCoinSchemas(coinSchema, totalCoins, CoinType.D, coinSchemasList);

                }
            }

            if(coinSchemaDB.getCoinTypeEnum().equals(CoinType.D)){
                nickels = Math.round((int)cents/5);
                if(coinSchemaDB.getCoinCount()>=nickels){

                    totalCoins +=nickels;
                    cents=cents%5;
                    coinSchemaDB.setCoinCount(coinSchemaDB.getCoinCount()-nickels);
                    //call service to update the count of NICKELS

                    return getCoinSchemas(coinSchema, totalCoins, CoinType.N, coinSchemasList);

                }
            }

            if(coinSchemaDB.getCoinTypeEnum().equals(CoinType.P)){
                pennies = Math.round((int)cents/1);
                if(coinSchemaDB.getCoinCount()>=pennies){

                    totalCoins +=pennies;
                    //cents=cents%1;
                    coinSchemaDB.setCoinCount(coinSchemaDB.getCoinCount()-pennies);
                    //call service to update the count of NICKELS

                    return getCoinSchemas(coinSchema, totalCoins, CoinType.P, coinSchemasList);

                }else {
                    return null;
                }
            }

        }

        System.out.println("total cents: " + cents);
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);
        System.out.println("totalCoins: " + totalCoins);


        return coinSchemasList;
    }

    private List<CoinSchema> getCoinSchemas(CoinSchema coinSchema, int totalCoins, CoinType p, List<CoinSchema> coinSchemasList) {
        coinSchema.setCoinCount(totalCoins);
        coinSchema.setCoinTypeEnum(p);
        coinSchemasList.add(coinSchema);
        return coinSchemasList;
    }

}
