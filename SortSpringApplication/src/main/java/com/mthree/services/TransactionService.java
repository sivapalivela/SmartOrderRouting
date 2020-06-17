package com.mthree.services;

import com.mthree.models.Consumers;
import com.mthree.models.TradingCompanies;
import com.mthree.models.TransactionBook;
import com.mthree.repositories.ConsumersRepository;
import com.mthree.repositories.TransactionRepository;
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

    public List<String> getTransactions(String userId){
        Optional<Consumers> c = consumerRepo.findById(userId);
        if(c.isPresent()){
            Consumers consumers = c.get();
            List<String> result = new ArrayList<>();
            List<TransactionBook> transactions = transRepo.findConsumerById(consumers.getConsumersId());
            result.add(String.valueOf(transactions.size()));
            for(TransactionBook tr : transactions){
                String arr[] = tr.getTypeOfTransaction().split(" ");
                String company = tr.getCompany_name().getCompanyId();
                String data = tr.getTransId() + "/" + tr.getNumberOfShares() + "/" + tr.getTimeStamp() + "/" + tr.getTransactionAmount() + "/" + arr[0] + "/" + company;
                result.add(data);
            }
            return result;
        }
        return null;
    }
}
