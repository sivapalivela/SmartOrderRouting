package com.mthree.controllers;

import com.mthree.models.OrderStock;
import com.mthree.repositories.DarkPoolTransRepository;
import com.mthree.repositories.TransactionRepository;
import com.mthree.services.SortService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/sort")
public class SortController {
    @Autowired
    private SortService sortService;

    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private DarkPoolTransRepository darkTransRepo;

    Logger logger = LoggerFactory.getLogger(SortController.class);

    @PostMapping("/processtrade/{id}/{range}")
    public JSONObject processTrade(@PathVariable() String id, @PathVariable String range){
        logger.info("A Process Trade request is received with in the range of" + range);
        return sortService.processTrade(Integer.parseInt(id), Integer.parseInt(range));
    }

    @GetMapping("/todayMarketValue")
    public double getTodayMarketValue() {
        logger.trace("A request has been received to get the total market value");
        return sortService.getTodayMarketValue();
    }

}
