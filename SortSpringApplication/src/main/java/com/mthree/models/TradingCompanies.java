package com.mthree.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TradingCompanies {
    @Id
    private String companyId;
    private String companyName;
    private String marketCap;

    @OneToOne(mappedBy = "company")
    private OrderStock exchange;

    public TradingCompanies() {
    }

    public TradingCompanies(String companyId, String companyName, String marketCap) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.marketCap = marketCap;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    @Override
    public String toString() {
        return "TradingCompanies{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", marketCap='" + marketCap + '\'' +
                '}';
    }
}
