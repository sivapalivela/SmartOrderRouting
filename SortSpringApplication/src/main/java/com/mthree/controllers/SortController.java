package com.mthree.controllers;

import com.mthree.models.OrderStock;
import com.mthree.services.SortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/sort")
public class SortController {
    @Autowired
    private SortService sortService;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/processtrade/{id}/{range}")
    public Set<OrderStock> processTrade(@PathVariable() String id, @PathVariable String range){
        logger.info("A Process Trade request is received with in the range of" + range);
        return sortService.processTrade(Integer.parseInt(id), Integer.parseInt(range));
    }

    @PostMapping("/executetrade/{buyerid}/{sellerid}")
    public String executeTrade(@PathVariable String buyerId, @PathVariable String sellerId){
        logger.info("A Execute Trade request has been called for the seller Id" + sellerId);
        return sortService.executeTrade(Integer.parseInt(buyerId), Integer.parseInt(sellerId));
    }
}
