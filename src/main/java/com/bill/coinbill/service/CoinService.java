package com.bill.coinbill.service;

import com.bill.coinbill.entity.Coin;
import com.bill.coinbill.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoinService {

    @Autowired
    CoinRepository coinRepository;

    /*@Autowired
    List<CoinSchema> coinSchemasList;*/

    public List<Coin> getCoin(Integer bill){

        // logic for coin bill dispenser

        List<Coin> coinSchemasList = new ArrayList<>();

        List<Coin> coinCountLstFromDB = coinRepository.getCoinDetails();

        System.out.println("coinCountLstFromDB size: " + coinCountLstFromDB.size());

        int totalCoins = 0;
        int quarters =0;
        int dimes =0;
        int nickels=0;
        int pennies=0;
        int cents = (int) Math.round(100*bill);

        Coin coinSchema = new Coin();
        //Collections.sort(coinCountLstFromDB);
        /*Collections.sort(CoinType, new Comparator<CoinType>() {
            @Override
            public int compare(CoinType person1, CoinType person2) {
                if (person1.toString() == person2.getSeverity()) {
                    return person1.getName().compareTo(person2.getName());
                } else {
                    return person1.getSeverity().compareTo(person2.getSeverity());
                }
            }
        });*/

        for(Coin coinSchemaDB :  coinCountLstFromDB){

            System.out.println("coinSchema Type: " + coinSchema.getCoinType());
            System.out.println("coinSchema Value: " + coinSchema.getCoinCount());

            if(coinSchemaDB.getCoinType().equals("Q")){
                quarters = Math.round((int)cents/25);
                if(coinSchemaDB.getCoinCount()>=quarters){

                    totalCoins +=quarters;
                    cents=cents%25;
                    coinSchemaDB.setCoinCount(coinSchemaDB.getCoinCount()-quarters);
                    //call service to update the count of quarters

                    return getCoinSchemas(coinSchema, totalCoins, "Q", coinSchemasList);

                }

            }

            if(coinSchemaDB.getCoinType().equals("D")){
                dimes = Math.round((int)cents/10);
                if(coinSchemaDB.getCoinCount()>=dimes){

                    totalCoins +=dimes;
                    cents=cents%10;
                    coinSchemaDB.setCoinCount(coinSchemaDB.getCoinCount()-dimes);
                    //call service to update the count of DIMES

                    return getCoinSchemas(coinSchema, totalCoins, "D", coinSchemasList);

                }
            }

            if(coinSchemaDB.getCoinType().equals("N")){
                nickels = Math.round((int)cents/5);
                if(coinSchemaDB.getCoinCount()>=nickels){

                    totalCoins +=nickels;
                    cents=cents%5;
                    coinSchemaDB.setCoinCount(coinSchemaDB.getCoinCount()-nickels);
                    //call service to update the count of NICKELS

                    return getCoinSchemas(coinSchema, totalCoins, "N", coinSchemasList);

                }
            }

            if(coinSchemaDB.getCoinType().equals("P")){
                pennies = Math.round((int)cents/1);
                if(coinSchemaDB.getCoinCount()>=pennies){

                    totalCoins +=pennies;
                    //cents=cents%1;
                    coinSchemaDB.setCoinCount(coinSchemaDB.getCoinCount()-pennies);
                    //call service to update the count of NICKELS

                    return getCoinSchemas(coinSchema, totalCoins, "P", coinSchemasList);

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

    private List<Coin> getCoinSchemas(Coin coinSchema, int totalCoins, String p, List<Coin> coinSchemasList) {
        coinSchema.setCoinCount(totalCoins);
        coinSchema.setCoinType(p);
        coinSchemasList.add(coinSchema);
        return coinSchemasList;
    }

}
