package com.mthree.controllers;

import com.mthree.models.OrderStock;
import com.mthree.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder/{companyId}")
    public String CreateOrder(@RequestBody OrderStock o, @PathVariable String companyId){
        return orderService.createOrder(o,companyId);
    }


}
