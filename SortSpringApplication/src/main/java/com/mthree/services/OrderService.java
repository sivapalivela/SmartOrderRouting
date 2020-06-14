package com.mthree.services;

import com.mthree.models.Exchange;
//import com.mthree.models.Order;
import com.mthree.models.OrderStock;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ExchangeRepository exchangeRepo;

    public String createOrder(OrderStock o){
        Optional<Exchange> exchangeObject = exchangeRepo.findById(o.getorderExchangeId());
        String message = "Failed to add !!!";
        if(exchangeObject.isPresent()){
            Exchange exObject = exchangeObject.get();
            o.setExchange(exObject);
            orderRepo.save(o);
            exObject.getBuyOrderBook().add(o);
            exchangeRepo.save(exObject);
            message = "Successfully added !!!";
        }
        return message;
    }

    public void cancelOrder(OrderStock o){
        orderRepo.deleteById(o.getOrderId());
    }


}
