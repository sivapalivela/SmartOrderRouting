package com.mthree.controllers;

import com.mthree.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/getexchange")
    public List<String> getExchange(){
        return exchangeService.getExchange();
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/getTodayTransValue")
    public double getTransValue(){
        return exchangeService.getTransValue();
    }
}
