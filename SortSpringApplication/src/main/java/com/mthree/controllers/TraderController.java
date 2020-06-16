package com.mthree.controllers;

import com.mthree.models.Consumers;
import com.mthree.models.Trader;
import com.mthree.services.ConsumersService;
import com.mthree.services.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trader")
public class TraderController {
    @Autowired
    private TraderService traderService;

    @PostMapping("/createtrader/{exchangeid}")
    public String createConsumer(@RequestBody Trader t, @PathVariable String exchangeid){
        return traderService.createTraders(t,exchangeid);
    }
}
