package com.bill.coinbill.controller;

import com.bill.coinbill.entity.Coin;
import com.bill.coinbill.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class CoinController {

    @Autowired
    CoinService coinService;

    @GetMapping(path = "/{bill}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCoin(@PathVariable Integer bill){

        if(bill==1 || bill==2 || bill==5 || bill==10 || bill==20 || bill==50 || bill ==100){
            List<Coin> coinSchemasList = coinService.getCoin(bill);
            if(coinSchemasList!=null){
                return new ResponseEntity<String>(coinSchemasList.toString(), HttpStatus.OK);
            }else {
                return new ResponseEntity<String>("There is no available coins for the bill provided, please try again later ",
                        HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity<String>("The bill entered is not valid, please try with 1 or 2 or 5 or 10 or 20 or 50 or 100$ Bill ",
                    HttpStatus.BAD_REQUEST);
        }
    }

    /*@GetMapping(path = "/{coinStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCoinStatusFromDB(){
        List<Coin> coinList = coinService.getCoinStatus();
        if(coinList!=null){
            return new ResponseEntity<String>(coinList.toString(), HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("No Coins in Database ",
                    HttpStatus.NOT_FOUND);
        }
    }*/

}
