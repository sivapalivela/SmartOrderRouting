package com.mthree.controllers;

import com.mthree.services.TradingCompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class TradingCompaniesController {
    @Autowired
    private TradingCompaniesService tradeService;

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/getcompanies")
    public List<String> getCompanies(){
        return tradeService.getCompanies();
    }
}
