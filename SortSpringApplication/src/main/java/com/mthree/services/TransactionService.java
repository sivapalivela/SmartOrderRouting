package com.mthree.services;

import com.mthree.models.Consumers;
import com.mthree.models.DarkPoolTransactionBook;
import com.mthree.models.TradingCompanies;
import com.mthree.models.TransactionBook;
import com.mthree.repositories.ConsumersRepository;
import com.mthree.repositories.DarkPoolTransRepository;
import com.mthree.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private ConsumersRepository consumerRepo;

    @Autowired
    private DarkPoolTransRepository darktransRepo;

    Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public List<String> getTransactions(String userId){
        Optional<Consumers> c = consumerRepo.findById(userId);
        if(c.isPresent()){
            Consumers consumers = c.get();
            List<String> result = new ArrayList<>();
            List<TransactionBook> transactions = transRepo.findConsumerById(consumers.getConsumersId());
            //testing
            List<DarkPoolTransactionBook> darkTransactions = darktransRepo.findConsumerById(consumers.getConsumersId());
            result.add(String.valueOf(transactions.size() + darkTransactions.size()));
            //testing
            for(TransactionBook tr : transactions){
                String arr[] = tr.getTypeOfTransaction().split(" ");
                String company = tr.getCompany_name().getCompanyId();
                String data = tr.getTransId() + "/" + tr.getNumberOfShares() + "/" + tr.getTimeStamp() + "/" + tr.getTransactionAmount() + "/" + arr[0] + "/" + company;
                result.add(data);
            }
            //test
            for(DarkPoolTransactionBook dp : darkTransactions){
                String arr[] = dp.getTypeOfTransaction().split(" ");
                String company = dp.getTrading_company();
                String data = dp.getTransId() + "/" + dp.getNumberOfShares() + "/" + dp.getTimeStamp() + "/" + dp.getTransactionAmount() + "/" + arr[0] + "/" + company;
                result.add(data);
            }
            //test
            logger.trace("All the available transactions are sent to the userinterface");
            return result;
        }
        logger.trace("No available transactions");
        return null;
    }
}
