package com.mthree.controllers;

import com.mthree.models.OrderStock;
import com.mthree.services.OrderService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderController.class);
    @CrossOrigin("http://localhost:4200")
    @PostMapping(value = "/createOrder/{username}/{companyId}")
    public String CreateOrder(@RequestBody OrderStock o, @PathVariable String companyId, @PathVariable String username){
        logger.info("A Create Order Request has been received for the company :" + companyId);
        return orderService.createOrder(o,companyId,username);
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/getorders")
    public List<String> getOrders(){
        return orderService.getOrders();
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping(value = "deleteOrder/{orderid}")
    public JSONObject deleteOrder(@PathVariable String orderid){
        return orderService.cancelOrder(Integer.parseInt(orderid));
    }



}
