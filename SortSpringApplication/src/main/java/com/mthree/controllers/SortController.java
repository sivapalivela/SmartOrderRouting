package com.mthree.controllers;

import com.mthree.services.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sort")
public class SortController {
    @Autowired
    private SortService sortService;

    @PostMapping("/trade/{id}")
    public String tradeOn(@PathVariable() String id){
        return sortService.executeTrade(Integer.parseInt(id));
    }
}
