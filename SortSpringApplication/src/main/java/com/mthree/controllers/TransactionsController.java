package com.mthree.controllers;

import com.mthree.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionService transactionService;

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/getTransactions/{userId}")
    public List<String> getTransactions(@PathVariable String userId){
        return transactionService.getTransactions(userId);
    }
}
