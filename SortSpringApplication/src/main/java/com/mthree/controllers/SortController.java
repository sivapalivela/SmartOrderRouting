package com.mthree.controllers;

import com.mthree.models.OrderStock;
import com.mthree.services.SortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/sort")
public class SortController {
    @Autowired
    private SortService sortService;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/processtrade/{id}/{range}")
    public String processTrade(@PathVariable() String id, @PathVariable String range){
        logger.info("A Process Trade request is received with in the range of" + range);
        return sortService.processTrade(Integer.parseInt(id), Integer.parseInt(range));
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/executetrade/{buyerid}/{sellerid}")
    public String executeTrade(@PathVariable String buyerId, @PathVariable String sellerId){
        logger.info("A Execute Trade request has been called for the seller Id" + sellerId);
        return sortService.executeTrade(Integer.parseInt(buyerId), Integer.parseInt(sellerId));
    }
}
