package com.mthree.controllers;

import com.mthree.LoginDAO.Login;
import com.mthree.models.Consumers;
import com.mthree.services.ConsumersService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumers")
public class ConsumersController {

    @Autowired
    private ConsumersService consumersService;

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/createconsumer/{exchangeid}")
    public String createConsumer(@RequestBody Consumers c, @PathVariable String exchangeid){
        return consumersService.createConsumers(c,exchangeid);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/login")
    public JSONObject loginUser(@RequestBody Login login){
        System.out.println(login.getUsername() + " " + login.getPassword());
        return consumersService.login(login.getUsername(), login.getPassword(), login.getTypeofuser());
    }
}
