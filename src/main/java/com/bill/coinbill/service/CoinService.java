package com.bill.coinbill.service;

import com.bill.coinbill.entity.Coin;
import com.bill.coinbill.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoinService {

    @Autowired
    CoinRepository coinRepository;



    public List<Coin> getCoin(Integer bill){

        // logic for bill coin dispenser
        //List for response entity
        List<Coin> coinVOList = new ArrayList<>();

        List<Coin> coinCountLstFromDB = coinRepository.getCoinDetails();
        System.out.println("coinCountLstFromDB size: " + coinCountLstFromDB.size());

        LinkedHashMap<String, Long> linkedHashMap = new LinkedHashMap<>();

        for(Coin coinValue : coinCountLstFromDB){
            linkedHashMap.put(coinValue.getCoinType(), coinValue.getCoinCount());
        }


        int quarters =0;
        int dimes =0;
        int nickels=0;
        int pennies=0;
        int cents = Math.round(100*bill);

        Coin coin = new Coin();

        for (Map.Entry<String, Long> coinEntry : linkedHashMap.entrySet()) {
            System.out.println(coinEntry.getKey() + ":" + coinEntry.getValue());

            //@Todo - Sum of all cointCount in DB and verify with cents -> Return NUll since no available coin conversion rate available.

            quarters = Math.round((int)cents/25);
            dimes = Math.round((int)cents/10);
            nickels = Math.round((int)cents/5);
            pennies = Math.round((int)cents/1);

            if(Objects.equals(coinEntry.getKey(), "Q") && coinEntry.getValue()!=0 && cents!=0){

                //logic to allocate coins
                // case1 - Q - 20 Count -> 5$ -> 500 Cents  || Allocate 500 cents and rest Dimes
                // coinEntry.getValue() -> 20
                // 20 * 0.25 *100 -> 500 Cents
                // Total Cents -> 1000 cents
                int totalCoins = 0;

                if(coinEntry.getValue()<quarters){
                    int centsToAllocateQuarters = (int) (cents - Math.round(coinEntry.getValue()* 0.25 * 100));
                    quarters = Math.round((int)centsToAllocateQuarters/25);
                    totalCoins +=quarters;
                    String typeOfCoin = "Q";
                    extracted(totalCoins, coinVOList, typeOfCoin); // assigning the VO Coin List for UI
                    cents = cents - centsToAllocateQuarters;

                    int coinCount = (int) (coinEntry.getValue()-quarters);
                    coin.setId(1);
                    coin.setCoinType("Q");
                    coin.setCoinCount(coinCount);

                    coinRepository.save(coin);
                }else {
                    totalCoins +=quarters;
                    cents=cents%25;
                    String typeOfCoin = "Q";
                    extracted(totalCoins, coinVOList, typeOfCoin);
                    //logic to persist in DB
                    int coinCount = (int) (coinEntry.getValue()-quarters);
                    coin.setId(1);
                    coin.setCoinType("Q");
                    coin.setCoinCount(coinCount);

                    coinRepository.save(coin);
                }

               // return coinVOList;

            }else if(Objects.equals(coinEntry.getKey(), "D") && coinEntry.getValue()!=0 && cents!=0) {

                // 500 Cents -> Assign to Full
                //

                int totalCoins = 0;

                if(coinEntry.getValue()<dimes){
                    int centsToAllocateDimes = (int) (cents - Math.round(coinEntry.getValue()* 0.10 * 100));
                    dimes = Math.round((int)centsToAllocateDimes/10);
                    totalCoins +=dimes;
                    String typeOfCoin = "D";
                    extracted(totalCoins, coinVOList, typeOfCoin); // assigning the VO Coin List for UI
                    cents = cents - centsToAllocateDimes;

                    int coinCount = (int) (coinEntry.getValue()-dimes);
                    coin.setId(2);
                    coin.setCoinType("D");
                    coin.setCoinCount(coinCount);

                    coinRepository.save(coin);
                }else {
                    totalCoins +=dimes;
                    cents=cents%10;
                    //Logic to return the list to UI
                    String typeOfCoin = "D";
                    extracted(totalCoins, coinVOList, typeOfCoin);
                    //logic to persist remaining coins
                    int coinCount = (int) (coinEntry.getValue()-dimes);
                    coin.setId(2);
                    coin.setCoinType("D");
                    coin.setCoinCount(coinCount);

                    coinRepository.save(coin);
                }

                //return coinVOList;
            }else if(Objects.equals(coinEntry.getKey(), "N") && coinEntry.getValue()!=0 && cents!=0) {

                int totalCoins = 0;

                if(coinEntry.getValue()<nickels){
                    int centsToAllocateNickels = (int) (cents - Math.round(coinEntry.getValue()* 0.05 * 100));
                    nickels = Math.round((int)centsToAllocateNickels/5);
                    totalCoins +=nickels;
                    String typeOfCoin = "N";
                    extracted(totalCoins, coinVOList, typeOfCoin); // assigning the VO Coin List for UI
                    cents = cents - centsToAllocateNickels;

                    int coinCount = (int) (coinEntry.getValue()-nickels);
                    coin.setId(3);
                    coin.setCoinType("N");
                    coin.setCoinCount(coinCount);

                    coinRepository.save(coin);
                }else {
                    totalCoins +=nickels;
                    cents=cents%5;
                    //Logic to return the list to UI
                    String typeOfCoin = "N";
                    extracted(totalCoins, coinVOList, typeOfCoin);
                    //logic to persist remaining coins
                    int coinCount = (int) (coinEntry.getValue()-nickels);
                    coin.setId(3);
                    coin.setCoinType("N");
                    coin.setCoinCount(coinCount);

                    coinRepository.save(coin);
                }

                //return coinVOList;
            }else if(Objects.equals(coinEntry.getKey(), "P") && coinEntry.getValue()!=0 && cents!=0) {
                int totalCoins = 0;

                totalCoins +=pennies;
                cents=cents%1;
                //Logic to return the list to UI
                String typeOfCoin = "P";
                extracted(totalCoins, coinVOList, typeOfCoin);
                //logic to persist remaining coins
                int coinCount = (int) (coinEntry.getValue()-pennies);
                coin.setId(4);
                coin.setCoinType("P");
                coin.setCoinCount(coinCount);

                coinRepository.save(coin);

                //return coinVOList;
            }


        }

        System.out.println("total cents: " + cents);
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);

        if(coinVOList.size()>0){
            return coinVOList; // When all coins are finished, then the final size will be 0
        }else {
            return null;
        }
        //return coinVOList;

    }

    private void extracted(int totalCoins, List<Coin> coinVOList, String typeOfCoin) {
        //Logic to return the list to UI
        Coin coinVO = new Coin();
        coinVO.setCoinCount(totalCoins);
        coinVO.setCoinType(typeOfCoin);
        coinVOList.add(coinVO);
    }


    public List<Coin> getCoinStatus(){
        List<Coin> coinCountLstFromDB = coinRepository.getCoinDetails();
        return coinCountLstFromDB;
    }


    private List<Coin> getCoinSchemas(Coin coinSchema, int totalCoins, String p, List<Coin> coinSchemasList) {
        coinSchema.setCoinCount(totalCoins);
        coinSchema.setCoinType(p);
        coinSchemasList.add(coinSchema);
        return coinSchemasList;
    }

}
