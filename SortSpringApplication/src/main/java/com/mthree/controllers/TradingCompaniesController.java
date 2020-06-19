package com.mthree.controllers;

import com.mthree.services.TradingCompaniesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/companies")
public class TradingCompaniesController {
    @Autowired
    private TradingCompaniesService tradeService;

    Logger logger = LoggerFactory.getLogger(TradingCompaniesController.class);

    @GetMapping("/getcompanies")
    public List<String> getCompanies(){
        logger.trace("A request has been called to get the list of all companies");
        return tradeService.getCompanies();
    }
}
