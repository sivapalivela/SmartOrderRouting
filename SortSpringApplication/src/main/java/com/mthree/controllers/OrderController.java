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
@CrossOrigin("http://localhost:4200")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping(value = "/createOrder/{username}/{companyId}")
    public String CreateOrder(@RequestBody OrderStock o, @PathVariable String companyId, @PathVariable String username){
        return orderService.createOrder(o,companyId,username);
    }

    @GetMapping("/getorders")
    public List<String> getOrders(){
        logger.trace("A request has been called to get the list of orders which are avaliable in the orderbook");
        return orderService.getOrders();
    }

    @PostMapping(value = "deleteOrder/{orderid}")
    public JSONObject deleteOrder(@PathVariable String orderid){
        logger.trace("A request has been received to delete the order with order Id : "+ orderid);
        return orderService.cancelOrder(Integer.parseInt(orderid));
    }



}
