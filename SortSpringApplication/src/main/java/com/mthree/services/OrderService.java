package com.mthree.services;

import com.mthree.models.Exchange;
import com.mthree.models.OrderStock;
import com.mthree.models.TradingCompanies;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.OrderRepository;
import com.mthree.repositories.TradingCompaniesRepository;
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
        }
        return message;
    }

    public void cancelOrder(OrderStock o){
        orderRepo.deleteById(o.getOrderId());
    }


}
