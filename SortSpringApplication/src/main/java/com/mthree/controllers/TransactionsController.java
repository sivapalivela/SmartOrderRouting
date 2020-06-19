package com.mthree.controllers;

import com.mthree.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    Logger logger = LoggerFactory.getLogger(TransactionsController.class);

    @GetMapping("/getTransactions/{userId}")
    public List<String> getTransactions(@PathVariable String userId){
        logger.trace("A request has been received to get the list of transactions of the user with id"+ userId);
        return transactionService.getTransactions(userId);
    }
}
