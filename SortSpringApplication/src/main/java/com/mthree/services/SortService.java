package com.mthree.services;

import com.mthree.controllers.OrderController;
import com.mthree.models.*;
import com.mthree.repositories.*;
import net.bytebuddy.asm.Advice;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SortService {

    @Autowired
    private ExchangeRepository exchangeRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private ConsumersRepository consumerRepo;

    @Autowired
    private TradingCompaniesRepository tradeComRepo;

    @Autowired
    private SortRepository sortRepo;

    @Autowired
    private DarkPoolOrderRepository darkOrderRepo;

    @Autowired
    private DarkPoolTransRepository darkTransRepo;


    Logger logger = LoggerFactory.getLogger(OrderController.class);

    public JSONObject processTrade(int id, int range) {
        Optional<OrderStock> o = orderRepo.findById(id);
        JSONObject jo = new JSONObject();
        String message = "Failed to process !!!";
        if (o.isPresent()) {
            OrderStock buyOrderObject = o.get();
            List<Exchange> exchangesList = exchangeRepo.findAll();
            HashMap<Integer, Double> result = new HashMap<>();
            TradingCompanies tc = buyOrderObject.getCompany();
            if (buyOrderObject.getTypeOfOrder().equals("BuyOrder")) {
                if (sortRepo.findAll().size() > 0) {
                    List<Sort> sortList = sortRepo.findAll();
                    for(Sort so : sortList){
                        for(DarkPoolOrderBook darkOrder : so.getOrderBook()){
                            if(darkOrder.getCompany().equals(tc.getCompanyId()) &&
                                    darkOrder.getTypeOfOrder().equals("SellOrder") &&
                                    Math.round(darkOrder.getPrice()) == Math.round(buyOrderObject.getPrice()) &&
                                    darkOrder.getNumberOfShares() == buyOrderObject.getNumberOfShares()){

                                DarkPoolTransactionBook darkTransaction = new DarkPoolTransactionBook();
                                String genId = buyOrderObject.getOrderId() + "BDarkS" + darkOrder.getOrderId();
                                darkTransaction.setTransId(genId);
                                logger.info("Transaction Successful !!!" + "The transaction id id"+ genId);
                                darkTransaction.setBuyerOrderId(buyOrderObject.getOrderId());
                                darkTransaction.setSellerOrderId(darkOrder.getOrderId());
                                darkTransaction.setBuyerSideExchange(darkOrder.getOrderExchangeId());
                                darkTransaction.setSellerSideExchange(darkOrder.getOrderExchangeId());
                                darkTransaction.setTransactionAmount(buyOrderObject.getNumberOfShares()*buyOrderObject.getPrice());
                                darkTransaction.setNumberOfShares(buyOrderObject.getNumberOfShares());
                                darkTransaction.setTimeStamp(java.time.LocalDate.now());
                                Consumers c = buyOrderObject.getConsumers();
                                Optional<Consumers> consumer_dark = consumerRepo.findById(c.getConsumersId());
                                if(consumer_dark.isPresent()){
                                    darkTransaction.setConsumer(consumer_dark.get().getConsumersId());
                                }
                                Sort dark_sort = darkOrder.getSort();
                                Optional<Sort> d = sortRepo.findById(dark_sort.getId());
                                if(d.isPresent()){
                                    darkTransaction.setSort(d.get());
                                }
                                darkTransaction.setTrading_company(darkOrder.getCompany());
                                darkTransRepo.save(darkTransaction);
                                orderRepo.deleteByOrderId(buyOrderObject.getOrderId());
                                darkOrderRepo.deleteByOrderId(darkOrder.getOrderId());
                                message = "Transaction successful !!!";
                                jo.put("text",message);
                                return jo;
                            }
                        }
                    }
                }
                for (Exchange ex : exchangesList) {
                    for (OrderStock tempOrderObject : ex.getOrderBook()) {
                        TradingCompanies tcSeller = tempOrderObject.getCompany();
                        if (tc.getCompanyId().equals(tcSeller.getCompanyId()) && tempOrderObject.getTypeOfOrder().equals("SellOrder") && Math.abs(tempOrderObject.getPrice() - buyOrderObject.getPrice()) <= range) {
                            result.put(tempOrderObject.getOrderId(), tempOrderObject.getPrice());
                        }
                    }
                }

                List<Map.Entry<Integer, Double>> list =
                        new LinkedList<Map.Entry<Integer, Double>>(result.entrySet());
                Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
                    public int compare(Map.Entry<Integer, Double> o1,
                                       Map.Entry<Integer, Double> o2) {
                        return (o1.getValue()).compareTo(o2.getValue());
                    }
                });

                HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
                for (Map.Entry<Integer, Double> aa : list) {
                    temp.put(aa.getKey(), aa.getValue());
                }
                if(temp.size()>0) {
                    Map.Entry<Integer, Double> entry = temp.entrySet().iterator().next();
                    message = this.executeTrade(id, entry.getKey());
                    jo.put("text", message);
                    return jo;
                }
                else{
                    jo.put("text", "No Sellers found right now !!! Please try again later !!!");
                    return jo;
                }
            } else {
                message = "This is a Sell Order and you can only reject it !!!";
                jo.put("text" , message);
                return jo;
            }
        }
        jo.put("text" , message);
        return jo;
    }

    public String executeTrade(int buyId, int sellId){
        TransactionBook tb = new TransactionBook();
        Optional<OrderStock> buyOrderId = orderRepo.findById(buyId);
        Optional<OrderStock> sellOrderId = orderRepo.findById(sellId);
        if(buyOrderId.isPresent() && sellOrderId.isPresent()){
            OrderStock buyOrderIdObject = buyOrderId.get();
            OrderStock sellOrderIdObject = sellOrderId.get();
            int sellOrderRemaining = sellOrderIdObject.getNumberOfShares() - buyOrderIdObject.getNumberOfShares();
            int buyOrderRemaining = buyOrderIdObject.getNumberOfShares() - sellOrderIdObject.getNumberOfShares();

            String[] arr2 = sellOrderIdObject.getOrderStatus().split(" ");
            if (sellOrderRemaining <= 0) {
                orderRepo.deleteByOrderId(sellOrderIdObject.getOrderId());
            } else {
                String seller_status = "Partial " + arr2[1];
                orderRepo.updateOrderByOrderStatus(seller_status, sellOrderIdObject.getOrderId());
                orderRepo.updateOrderByNumberOfShares(sellOrderRemaining, sellOrderIdObject.getOrderId());
            }

            String[] arr = buyOrderIdObject.getOrderStatus().split(" ");
            if(buyOrderRemaining <=0 ){
                tb.setNumberOfShares(buyOrderIdObject.getNumberOfShares());
                tb.setTypeOfTransaction("Executed " + arr[1]);
                orderRepo.deleteByOrderId(buyOrderIdObject.getOrderId());
            }
            else{
                tb.setNumberOfShares(buyOrderIdObject.getNumberOfShares() - sellOrderIdObject.getNumberOfShares());
                String status = "Partial " + arr[1];
                tb.setTypeOfTransaction(status);
                orderRepo.updateOrderByOrderStatus(status, buyOrderIdObject.getOrderId());
                orderRepo.updateOrderByNumberOfShares(buyOrderRemaining,buyOrderIdObject.getOrderId());
            }

            String transId = buyOrderIdObject.getOrderId() + "BtransS" + sellOrderIdObject.getOrderId();
            tb.setTransId(transId);
            tb.setBuyerOrderId(buyOrderIdObject.getOrderId());
            tb.setSellerOrderId(sellOrderIdObject.getOrderId());
            tb.setBuyerSideExchange(buyOrderIdObject.getOrderExchangeId());
            tb.setSellerSideExchange(sellOrderIdObject.getOrderExchangeId());
            //untested
            double amountOfTransactions = buyOrderIdObject.getNumberOfShares()*buyOrderIdObject.getPrice();
            Optional<Exchange> opt = exchangeRepo.findById(buyOrderIdObject.getOrderExchangeId());
            Exchange tariffExchange = null;
            if(opt.isPresent()){
                tariffExchange = opt.get();
            }
            if(amountOfTransactions > 100000){
                tb.setTransactionAmount( amountOfTransactions + (amountOfTransactions*(tariffExchange.getBulkFeeConstant()/100)));
            }
            else {
                tb.setTransactionAmount( amountOfTransactions + (amountOfTransactions*(tariffExchange.getFeeConstant()/100)));
            }
            tariffExchange.setOverallTransactionValue(tariffExchange.getOverallTransactionValue() + tb.getTransactionAmount());
            //untested
            Optional<Consumers> cObject = consumerRepo.findById(buyOrderIdObject.getConsumers().getConsumersId());
            if(cObject.isPresent()){
                Consumers c = cObject.get();
                c.setTransactedAmountTillNow((long)(c.getTransactedAmountTillNow() + tb.getTransactionAmount()));
            }
            tb.setTimeStamp(java.time.LocalDate.now());
            Optional<Exchange> buyExchange = exchangeRepo.findById(buyOrderIdObject.getOrderExchangeId());
            TradingCompanies tCompany = buyOrderIdObject.getCompany();
            Optional<TradingCompanies> tradeCompany = tradeComRepo.findById(tCompany.getCompanyId());
            Consumers c = buyOrderIdObject.getConsumers();
            Optional<Consumers> consumer = consumerRepo.findById(c.getConsumersId());
            if(buyExchange.isPresent() && consumer.isPresent() && tradeCompany.isPresent()){
                tb.setExchange(buyExchange.get());
                tb.setConsumers(consumer.get());
                tb.setCompany_name(tradeCompany.get());
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

    public double getTodayMarketValue() {
        LocalDate t=java.time.LocalDate.now();
        String s=t.toString();
        double n = 0;
        double p = 0;
        if(transactionRepo.findAll().size() > 0){
            n += this.calculateMarketValue(transactionRepo.getTransactionShares(s));
            p += this.calculateMarketValue(transactionRepo.getTransactionAmount(s));
        }

        if(darkTransRepo.findAll().size() > 0){
            n += this.calculateMarketValue(darkTransRepo.getTransactionShares(s));
            p += this.calculateMarketValue(darkTransRepo.getTransactionAmount(s));
        }

        if(n>0 && p>0){
            double result = p/n;
            return Math.round(result*100)/100;
        }
        return 0;
    }

    public double calculateMarketValue(Double data){
        if(data != null){
            return data;
        }
        return 0;
    }


}
