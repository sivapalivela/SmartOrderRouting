package com.mthree.services;

import com.mthree.models.Consumers;
import com.mthree.models.Exchange;
import com.mthree.models.Trader;
import com.mthree.models.TradingCompanies;
import com.mthree.repositories.ConsumersRepository;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.TraderRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumersService {

    @Autowired
    private ConsumersRepository comsumersRepo;

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private TraderRepository tradeRepo;

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

    public JSONObject login(String username, String password, String typeofUser){
        JSONObject jo = new JSONObject();
        String message = "";
        if(typeofUser.equals("Trader")){
            Trader trader = tradeRepo.findTraderById(username, password);
            if(trader!=null){
                message = "Login Successful " + trader.getTraderId();
                jo.put("text",message);
                return jo;
            }
            else{
                jo.put("text","Trader doesn't exist with that details");
                return jo;
            }
        }
        else{
            Consumers consumer = comsumersRepo.findUserById(username, password);
            if(consumer!=null){
                message = "Login Successful " + consumer.getConsumersId();
                jo.put("text",message);
                return jo;
            }
            else{
                jo.put("text","Consumer doesn't exist with that details");
                return jo;
            }
        }
    }
}
