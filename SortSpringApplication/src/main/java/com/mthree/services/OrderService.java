package com.mthree.services;

import com.mthree.controllers.OrderController;
import com.mthree.models.Exchange;
import com.mthree.models.OrderStock;
import com.mthree.models.TradingCompanies;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.OrderRepository;
import com.mthree.repositories.TradingCompaniesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private TradingCompaniesRepository tradeComRepo;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    public String createOrder(OrderStock o, String companyId){
        Optional<Exchange> exchangeObject = exchangeRepo.findById(o.getOrderExchangeId());
        Optional<TradingCompanies> companyObject = tradeComRepo.findById(companyId);
        String message = "Failed to add stock !!!";
        if(exchangeObject.isPresent() && companyObject.isPresent()){
            TradingCompanies tcObject = companyObject.get();
            Exchange exObject = exchangeObject.get();
            o.setExchange(exObject);
            o.setCompany(tcObject);
            orderRepo.save(o);
            exObject.getOrderBook().add(o);
            exchangeRepo.save(exObject);
            message = "Successfully added stock !!!";
            logger.info("Order has been added to the order Stock");
        }
        if(message.equals("Failed to add stock"))
            logger.info("Failed to add stock");
        return message;
    }

    public void cancelOrder(OrderStock o){
        orderRepo.deleteById(o.getOrderId());
        logger.info("Order Id " + o.getOrderId()+ "is deleted");
    }


}
