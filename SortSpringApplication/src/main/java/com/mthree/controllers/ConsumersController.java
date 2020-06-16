package com.mthree.controllers;

import com.mthree.models.Consumers;
import com.mthree.services.ConsumersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumers")
public class ConsumersController {

    @Autowired
    private ConsumersService consumersService;

    @PostMapping("/createconsumer/{exchangeid}")
    public String createConsumer(@RequestBody Consumers c, @PathVariable String exchangeid){
        return consumersService.createConsumers(c,exchangeid);
    }
}
