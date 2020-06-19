package com.mthree.services;

import com.mthree.models.Consumers;
import com.mthree.models.Exchange;
import com.mthree.models.Trader;
import com.mthree.models.TradingCompanies;
import com.mthree.repositories.ConsumersRepository;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.TraderRepository;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumersService {

    Logger logger = LoggerFactory.getLogger(ConsumersService.class);

    @Autowired
    private ConsumersRepository comsumersRepo;

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private TraderRepository tradeRepo;

    public List<String> getDetails(String username){
        Optional<Consumers> consumer = comsumersRepo.findById(username);
        List<String> result = new ArrayList<>();
        if(consumer.isPresent()){
            result.add(String.valueOf(6));
            Consumers c = consumer.get();
            result.add(c.getFirstName());
            result.add(c.getLastName());
            result.add(c.getEmail());
            result.add(c.getLocation());
            result.add(c.getMobileNumber());
            result.add(String.valueOf(c.getTransactedAmountTillNow()));
            return result;
        }
        result.add("0");
        result.add("No Data about user");
        return result;
    }

    public String createConsumers(Consumers c, String excId){
        Optional<Exchange> exchangeObject = exchangeRepo.findById(excId);
        String message = "Failed to add user !!!";
        if(exchangeObject.isPresent()){
            Exchange exObject = exchangeObject.get();
            c.setExchangeOfConsumers(exObject);
            comsumersRepo.save(c);
            message = "Successfully added user " + c.getConsumersId() + " !";
        }
        logger.trace(message);
        return message;
    }

    public JSONObject login(String username, String password, String typeofUser){
        JSONObject jo = new JSONObject();
        String message = "";
        if(typeofUser.equals("Trader")){
            Trader trader = tradeRepo.findTraderById(username, password);
            if(trader!=null){
                message = "Login Successful " + trader.getTraderId() + " " + trader.getExchange().getExchangeId();
                logger.trace(message);
                jo.put("text",message);
                return jo;
            }
            else{
                jo.put("text","Trader doesn't exist with that details");
                logger.trace("Trader doesn't exist with that details");
                return jo;
            }
        }
        else{
            Consumers consumer = comsumersRepo.findUserById(username, password);
            if(consumer!=null){
                message = "Login Successful " + consumer.getConsumersId() + " " + consumer.getExchangeOfConsumers().getExchangeId();
                jo.put("text",message);
                logger.trace(message);
                return jo;
            }
            else{
                jo.put("text","Consumer doesn't exist with that details");
                logger.trace("Consumer doesn't exist with that details");
                return jo;
            }
        }
    }
}
