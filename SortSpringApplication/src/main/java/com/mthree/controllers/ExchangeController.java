package com.mthree.controllers;

import com.mthree.services.ExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    @GetMapping("/getexchange")
    public List<String> getExchange(){
        logger.trace("A request has been sent to get the list of exchanges");
        return exchangeService.getExchange();
    }

    @GetMapping("/getTodayTransValue")
    public double getTransValue(){
        logger.trace("A request to get todays transaction value has been called");
        return exchangeService.getTransValue();
    }

    @GetMapping("/overallTransValue/{exchange}")
    public double overallTransValue(@PathVariable String exchange){
        logger.trace("A request to get over all transaction value has been called");
        return exchangeService.overallTransValue(exchange);
    }

}
