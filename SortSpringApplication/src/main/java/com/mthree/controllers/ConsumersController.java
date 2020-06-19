package com.mthree.controllers;

import com.mthree.LoginDAO.Login;
import com.mthree.models.Consumers;
import com.mthree.services.ConsumersService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/consumers")
public class ConsumersController {

    @Autowired
    private ConsumersService consumersService;

    Logger logger = LoggerFactory.getLogger(ConsumersController.class);

    @PostMapping("/createconsumer/{exchangeid}")
    public String createConsumer(@RequestBody Consumers c, @PathVariable String exchangeid){
        logger.trace("A new customer creation request has been received");
        return consumersService.createConsumers(c,exchangeid);
    }

    @PostMapping("/login")
    public JSONObject loginUser(@RequestBody Login login){
        logger.trace("User" + login.getUsername()+"trying to be logging in");
        return consumersService.login(login.getUsername(), login.getPassword(), login.getTypeofuser());
    }

    @GetMapping("/getdetails/{username}")
    public List<String> getDetails(@PathVariable String username){
        System.out.println(username);
        return consumersService.getDetails(username);
    }

}
