package com.mthree.services;

import com.mthree.models.Exchange;
import com.mthree.models.TradingCompanies;
import com.mthree.repositories.DarkPoolTransRepository;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.TradingCompaniesRepository;
import com.mthree.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService {

    Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private DarkPoolTransRepository transDarkRepo;

    public List<String> getExchange(){
        List<String> exchanges = new ArrayList<>();
        List<Exchange> obtainList = exchangeRepo.findAll();
        exchanges.add(String.valueOf(obtainList.size()));
        for(Exchange t : obtainList){
            exchanges.add(t.getExchangeName() + " - " + t.getExchangeId());
        }
        logger.trace("All the avaliable exchanges are sent to the UI");
        return exchanges;
    }

    public double getTransValue(){
        LocalDate d = java.time.LocalDate.now();
        String date = d.toString();
        double total = 0;
        if(transRepo.findAll().size() > 0){
            Double amount = transRepo.getTransactionAmount(date);
            if(amount != null)
                total += amount;
        }
        if(transDarkRepo.findAll().size() > 0){
            Double amount = transDarkRepo.getTransactionAmount(date);
            if(amount != null){
                total += amount;
            }
        }
        logger.trace("The total transaction value has been calculate and displayed which is :"+ total);
        return total;
    }

    public double overallTransValue(String exchange){
        Optional<Exchange> getExchange = exchangeRepo.findById(exchange);
        if(getExchange.isPresent()){
            double value = getExchange.get().getOverallTransactionValue();
            logger.trace("Overall transaction value of an exchange is sent back to the user and it is"+value);
            return value;
        }
        logger.trace("The over all transaction value of the exchange "+ exchange+"is 0");
        return 0;
    }
}
