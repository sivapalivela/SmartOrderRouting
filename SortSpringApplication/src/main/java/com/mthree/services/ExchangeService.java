package com.mthree.services;

import com.mthree.models.Exchange;
import com.mthree.models.TradingCompanies;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.TradingCompaniesRepository;
import com.mthree.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private TransactionRepository transRepo;

    public List<String> getExchange(){
        List<String> exchanges = new ArrayList<>();
        List<Exchange> obtainList = exchangeRepo.findAll();
        exchanges.add(String.valueOf(obtainList.size()));
        for(Exchange t : obtainList){
            exchanges.add(t.getExchangeName() + " - " + t.getExchangeId());
        }
        return exchanges;
    }

    public double getTransValue(){
        LocalDate d = java.time.LocalDate.now();
        String date = d.toString();
        return transRepo.getTransactionAmount(date);
//        return 0.0;
    }
}
