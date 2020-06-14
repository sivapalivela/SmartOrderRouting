package com.mthree.controllers;

import com.mthree.models.OrderStock;
import com.mthree.services.SortService;
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

    @PostMapping("/processtrade/{id}/{range}")
    public Set<OrderStock> processTrade(@PathVariable() String id, @PathVariable String range){
        return sortService.processTrade(Integer.parseInt(id), Integer.parseInt(range));
    }

    @PostMapping("/executetrade/{buyerid}/{sellerid}")
    public String executeTrade(@PathVariable String buyerId, @PathVariable String sellerId){
        return sortService.executeTrade(Integer.parseInt(buyerId), Integer.parseInt(sellerId));
    }
}
