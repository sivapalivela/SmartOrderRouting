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

    public OrderStock createOrder(OrderStock o){
        Optional<Exchange> ex = exchangeRepo.findById(o.getorderExchangeId());
        if(ex.isPresent()){
            Exchange exObject = ex.get();
            o.setExchange(exObject);
            orderRepo.save(o);
            if(o.getTypeOfOrder().equals("BuyOrder")){
                exObject.getBuyOrderBook().add(o);
                System.out.println(exObject.getBuyOrderBook().size());
            }
            else {
                exObject.getSellOrderBook().add(o);
                System.out.println(exObject.getSellOrderBook().size());
            }
            exchangeRepo.save(exObject);
            return o;
        }
        return null;
    }

    public void cancelOrder(OrderStock o){
        orderRepo.deleteById(o.getOrderId());
    }


}
