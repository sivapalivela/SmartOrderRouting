package com.mthree.services;

import com.mthree.controllers.OrderController;
import com.mthree.models.Consumers;
import com.mthree.models.Exchange;
import com.mthree.models.OrderStock;
import com.mthree.models.TradingCompanies;

import com.mthree.repositories.*;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private TradingCompaniesRepository tradeComRepo;

    @Autowired
    private ConsumersRepository consumerRepo;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    public String createOrder(OrderStock o, String companyId, String username){
        Optional<Exchange> exchangeObject = exchangeRepo.findById(o.getOrderExchangeId());
        Optional<TradingCompanies> companyObject = tradeComRepo.findById(companyId);
        Optional<Consumers> userObject = consumerRepo.findById(username);
        String message = "Failed to add stock !!!";
        if(exchangeObject.isPresent() && companyObject.isPresent() && userObject.isPresent()){
            TradingCompanies tcObject = companyObject.get();
            Exchange exObject = exchangeObject.get();
            Consumers usrObject = userObject.get();
            o.setExchange(exObject);
            o.setCompany(tcObject);
            o.setConsumers(usrObject);
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

    public List<String> getOrders(){
        List<String> orders = new ArrayList<>();
        List<OrderStock> obtainList = orderRepo.findAll();
        orders.add(String.valueOf(obtainList.size()));
        for(OrderStock o : obtainList){
            TradingCompanies trCompany = o.getCompany();
            Consumers c = o.getConsumers();
            String[] arr = o.getOrderStatus().split(" ");
            String data = o.getOrderId() + "-" + o.getNumberOfShares() + "-" + o.getTypeOfOrder() + "-" + o.getPrice() + "-" + arr[0] + "-" + trCompany.getCompanyId() + "-" + c.getConsumersId() + "-" + arr[1];
            orders.add(data);
        }
        return orders;
    }

    public JSONObject cancelOrder(int id){
        String message = "Failed to Delete !!!";
        JSONObject jo = new JSONObject();
        Optional<OrderStock> order = orderRepo.findById(id);
        if(order.isPresent()){
            orderRepo.deleteById(id);
            logger.info("Order Id " + id + " is deleted");
            message = "Successfully Deleted !!!";
            jo.put("text" , message);
            return jo;
        }
        jo.put("text" , message);
        return jo;
    }


}
