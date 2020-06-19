package com.mthree.services;

import com.mthree.models.TradingCompanies;
import com.mthree.repositories.TradingCompaniesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradingCompaniesService {
    @Autowired
    private TradingCompaniesRepository tradeCompaniesRepo;

    Logger logger = LoggerFactory.getLogger(TradingCompaniesService.class);

    public List<String> getCompanies(){
        List<String> companies = new ArrayList<>();
        List<TradingCompanies> obtainList = tradeCompaniesRepo.findAll();
        companies.add(String.valueOf(obtainList.size()));
        for(TradingCompanies t : obtainList){
            companies.add(t.getCompanyName() + " - " + t.getCompanyId());
        }
        logger.trace("All the available list of companies has been returned to the user interface");
        return companies;
    }
}
