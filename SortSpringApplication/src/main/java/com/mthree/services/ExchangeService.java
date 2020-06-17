package com.mthree.services;

import com.mthree.models.Exchange;
import com.mthree.models.TradingCompanies;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.TradingCompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepo;

    public List<String> getExchange(){
        List<String> exchanges = new ArrayList<>();
        List<Exchange> obtainList = exchangeRepo.findAll();
        exchanges.add(String.valueOf(obtainList.size()));
        for(Exchange t : obtainList){
            exchanges.add(t.getExchangeName() + " - " + t.getExchangeId());
        }
        return exchanges;
    }
}
