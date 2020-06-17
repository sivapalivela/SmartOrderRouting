package com.mthree.services;

import com.mthree.models.Consumers;
import com.mthree.models.Exchange;
import com.mthree.models.TradingCompanies;
import com.mthree.repositories.ConsumersRepository;
import com.mthree.repositories.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumersService {

    @Autowired
    private ConsumersRepository comsumersRepo;

    @Autowired
    private ExchangeRepository exchangeRepo;

    public String createConsumers(Consumers c, String excId){
        Optional<Exchange> exchangeObject = exchangeRepo.findById(excId);
        String message = "Failed to add user !!!";
        if(exchangeObject.isPresent()){
            Exchange exObject = exchangeObject.get();
            c.setExchangeOfConsumers(exObject);
            comsumersRepo.save(c);
            message = "Successfully added user " + c.getConsumersId() + " !!!";
        }
        return message;
    }
}
