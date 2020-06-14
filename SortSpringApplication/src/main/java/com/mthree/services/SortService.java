package com.mthree.services;

import com.mthree.models.Exchange;
import com.mthree.models.OrderStock;
import com.mthree.repositories.ExchangeRepository;
import com.mthree.repositories.OrderRepository;
import com.mthree.repositories.SortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SortService {
    @Autowired
    private SortRepository sortRepo;

    @Autowired
    private ExchangeRepository ExR;

    @Autowired
    private OrderRepository ord;

    public String executeTrade(int id) {
        Optional<OrderStock> o = ord.findById(id);
        List<Exchange> exchangeList = ExR.findAll();
        if (o.isPresent()) {
            OrderStock os = o.get();
            System.out.println(os.getOrderId()+ " " + os.getTypeOfOrder());
            if (os.getTypeOfOrder().equals("BuyOrder")) {
                // TODO : Search Dark Pool4
                double min = Double.MAX_VALUE;
                OrderStock o1 = null;
                Exchange exe = null;
                for (Exchange ex : exchangeList) {
                    for (OrderStock ob : ex.getSellOrderBook()) {
                        if (os.getPrice() <= ob.getPrice() && os.getPrice() - ob.getPrice() <= 10) {
                            if (ob.getPrice() <= min) {
                                min = ob.getPrice();
                                o1 = ob;
                                exe = ex;
                            }
                        }
                    }
                }
                System.out.println(exe.getSellOrderBook().size());
                int rem = o1.getNumberOfShares() - os.getNumberOfShares();
                if (rem == 0) {
                    exe.getSellOrderBook().remove(o1);
                } else {
                    ord.updateOrderByNumberOfShares(rem, o1.getOrderId());
                }
                String transId = o1.getOrderId() + " Transaction " + os.getOrderId();
                System.out.println(transId);
//                exe.getTransactions().put(transId, o1);
                Optional<Exchange> ex = ExR.findById(os.getorderExchangeId());
                if (ex.isPresent()) {
                    Exchange e1 = ex.get();
                    e1.getBuyOrderBook().remove(os);
                }
//                System.out.println(exe.getSellOrderBook());
                return transId;

            }
            else {
                // TODO : Search Dark Pool
                for (Exchange ex : exchangeList) {
                    for (OrderStock ob : ex.getSellOrderBook()) {
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
