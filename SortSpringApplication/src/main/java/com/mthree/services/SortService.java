package com.mthree.services;

import com.mthree.models.Exchange;
import com.mthree.models.OrderStock;
import com.mthree.models.TransactionBook;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.OrderRepository;
import com.mthree.repositories.SortRepository;
import com.mthree.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SortService {
    @Autowired
    private SortRepository sortRepo;

    @Autowired
    private ExchangeRepository ExR;

    @Autowired
    private OrderRepository ord;

    @Autowired
    private TransactionRepository tre;

    public String executeTrade(int id) {
        Optional<OrderStock> o = ord.findById(id);
        if (o.isPresent()) {
            List<Exchange> exchangeList = ExR.findAll();
            OrderStock os = o.get();
            System.out.println(os.getOrderId()+ " " + os.getTypeOfOrder());
            if (os.getTypeOfOrder().equals("BuyOrder")) {
                // TODO : Search Dark Pool4
                double min = Double.MAX_VALUE;
                OrderStock o1 = null;
                Exchange exe = null;
                for (Exchange ex : exchangeList) {
                    for (OrderStock ob : ex.getBuyOrderBook()) {
                        if (ob.getTypeOfOrder().equals("SellOrder") && os.getPrice() <= ob.getPrice() && os.getPrice() - ob.getPrice() <= 10) {
                            if (ob.getPrice() <= min) {
                                min = ob.getPrice();
                                o1 = ob;
                                exe = ex;
                            }
                        }
                    }
                }
//                System.out.println(exe.getBuyOrderBook());
                int sellerRem = o1.getNumberOfShares() - os.getNumberOfShares();
                int buyerRem = os.getNumberOfShares() - o1.getNumberOfShares();
                System.out.println(o1.getOrderId());
                if (sellerRem <= 0) {
                    ord.deleteByOrderId(o1.getOrderId());
                } else {
                    ord.updateOrderByNumberOfShares(sellerRem, o1.getOrderId());
                }

                if(buyerRem <=0 ){
                    ord.deleteByOrderId(os.getOrderId());
                }
                else{
                    ord.updateOrderByNumberOfShares(buyerRem,os.getOrderId());
                }
                String transId = o1.getOrderId() + " Transaction " + os.getOrderId();
                TransactionBook tb = new TransactionBook();
                tb.setTransId(transId);
                tb.setBuyerOrderId(os.getOrderId());
                tb.setSellerOrderId(o1.getOrderId());
                tb.setBuyerSideExchange(os.getorderExchangeId());
                tb.setSellerSideExchange(o1.getorderExchangeId());
                tb.setTransactionAmount(os.getNumberOfShares()*os.getPrice());
                tb.setNumberOfShares(os.getNumberOfShares());
                tb.setTimeStamp(new Date());
                tb.setExchange(exe);
                tre.save(tb);
                return transId;

            }
            else {
                // TODO : Search Dark Pool
                for (Exchange ex : exchangeList) {
                    for (OrderStock ob : ex.getBuyOrderBook()) {
                        if (os.getPrice() <= ob.getPrice()) {
//                            result.add(ob);
                        }
                    }
                }
            }
        }
        return null;
    }
}
