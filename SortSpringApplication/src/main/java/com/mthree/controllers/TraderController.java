package com.mthree.controllers;

import com.mthree.models.Consumers;
import com.mthree.models.Trader;
import com.mthree.services.ConsumersService;
import com.mthree.services.TraderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/trader")
public class TraderController {
    @Autowired
    private TraderService traderService;

    Logger logger = LoggerFactory.getLogger(TraderController.class);

    @PostMapping("/createtrader/{exchangeid}")
    public String createTrader(@RequestBody Trader t, @PathVariable String exchangeid){
        logger.trace("A request has been received to create a new trader ");
        return traderService.createTraders(t,exchangeid);
    }
}
