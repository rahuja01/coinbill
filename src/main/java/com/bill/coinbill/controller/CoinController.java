package com.bill.coinbill.controller;

import com.bill.coinbill.entity.CoinSchema;
import com.bill.coinbill.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{bill}")
    public List<CoinSchema> getCoin(@PathVariable Integer bill){

        List<CoinSchema> coinSchemasList = coinService.getCoin(bill);

        return coinSchemasList;
    }



}
