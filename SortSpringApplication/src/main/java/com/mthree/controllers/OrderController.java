package com.mthree.controllers;

import com.mthree.models.OrderStock;
import com.mthree.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderController.class);
    @PostMapping(value = "/createOrder/{username}/{companyId}")
    public String CreateOrder(@RequestBody OrderStock o, @PathVariable String companyId, @PathVariable String username){
        logger.info("A Create Order Request has been received for the company :" + companyId);
        return orderService.createOrder(o,companyId,username);
    }


}
