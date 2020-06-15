package com.mthree.services;

import com.mthree.controllers.OrderController;
import com.mthree.models.Exchange;
import com.mthree.models.OrderStock;
import com.mthree.models.TransactionBook;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.OrderRepository;
import com.mthree.repositories.SortRepository;
import com.mthree.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SortService {

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    public Set<OrderStock> processTrade(int id, int range) {
        Optional<OrderStock> o = orderRepo.findById(id);
        if (o.isPresent()) {
            OrderStock buyOrderObject = o.get();
            List<Exchange> exchangesList = exchangeRepo.findAll();
            Set<OrderStock> result = new HashSet<>();
            if (buyOrderObject.getTypeOfOrder().equals("BuyOrder")) {
                // TODO : Search Dark Pool
                for (Exchange ex : exchangesList) {
                    for (OrderStock tempOrderObject : ex.getOrderBook()) {
                        if (tempOrderObject.getTypeOfOrder().equals("SellOrder") && Math.abs(tempOrderObject.getPrice() - buyOrderObject.getPrice()) <= range) {
                            result.add(tempOrderObject);
                        }
                    }
                }
                return result;
            } else {
                return null;
            }
        }
        return null;
    }

    public String executeTrade(int buyId, int sellId){
        Optional<OrderStock> buyOrderId = orderRepo.findById(buyId);
        Optional<OrderStock> sellOrderId = orderRepo.findById(sellId);
        if(buyOrderId.isPresent() && sellOrderId.isPresent()){
            OrderStock buyOrderIdObject = buyOrderId.get();
            OrderStock sellOrderIdObject = sellOrderId.get();
            int sellOrderRemaining = sellOrderIdObject.getNumberOfShares() - buyOrderIdObject.getNumberOfShares();
            int buyOrderRemaining = buyOrderIdObject.getNumberOfShares() - sellOrderIdObject.getNumberOfShares();

            if (sellOrderRemaining <= 0) {
                orderRepo.deleteByOrderId(sellOrderIdObject.getOrderId());
            } else {
                orderRepo.updateOrderByNumberOfShares(sellOrderRemaining, sellOrderIdObject.getOrderId());
            }

            if(buyOrderRemaining <=0 ){
                orderRepo.deleteByOrderId(buyOrderIdObject.getOrderId());
            }
            else{
                orderRepo.updateOrderByNumberOfShares(buyOrderRemaining,buyOrderIdObject.getOrderId());
            }

            TransactionBook tb = new TransactionBook();
            String transId = buyOrderIdObject.getOrderId() + "BtransS" + sellOrderIdObject.getOrderId();
            tb.setTransId(transId);
            tb.setBuyerOrderId(buyOrderIdObject.getOrderId());
            tb.setSellerOrderId(sellOrderIdObject.getOrderId());
            tb.setBuyerSideExchange(buyOrderIdObject.getOrderExchangeId());
            tb.setSellerSideExchange(sellOrderIdObject.getOrderExchangeId());
            tb.setTransactionAmount(buyOrderIdObject.getNumberOfShares()*buyOrderIdObject.getPrice());
            tb.setNumberOfShares(buyOrderIdObject.getNumberOfShares());
            tb.setTimeStamp(new Date());
            Optional<Exchange> buyExchange = exchangeRepo.findById(buyOrderIdObject.getOrderExchangeId());
            if(buyExchange.isPresent()){
                tb.setExchange(buyExchange.get());
            }
            else{
                logger.info("Transaction creation failed !!!");
                return "Transaction creation failed !!!";
            }
            transactionRepo.save(tb);
            logger.info("Transaction Successful !!!" + "The transaction id id"+ transId);
            return "Transaction Successful !!!";

        }
        logger.info("Couldn't find Seller !!!");
        return "Couldn't find Seller !!!";
    }

}
