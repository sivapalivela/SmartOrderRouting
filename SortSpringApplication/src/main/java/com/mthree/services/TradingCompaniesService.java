package com.mthree.services;

import com.mthree.models.TradingCompanies;
import com.mthree.repositories.TradingCompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradingCompaniesService {
    @Autowired
    private TradingCompaniesRepository tradeCompaniesRepo;

    public List<String> getCompanies(){
        List<String> companies = new ArrayList<>();
        List<TradingCompanies> obtainList = tradeCompaniesRepo.findAll();
        companies.add(String.valueOf(obtainList.size()));
        for(TradingCompanies t : obtainList){
            companies.add(t.getCompanyName() + " - " + t.getCompanyId());
        }
        return companies;
    }
}
