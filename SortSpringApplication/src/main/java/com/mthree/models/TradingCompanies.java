package com.mthree.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TradingCompanies {
    @Id
    private String companyId;
    private String companyName;
    private String marketCap;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderStock> orderStock;

    @OneToMany(mappedBy = "company_name", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TransactionBook> trans_company;

    public TradingCompanies() {
    }

    public TradingCompanies(String companyId, String companyName, String marketCap, Set<OrderStock> orderStock) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.marketCap = marketCap;
        this.orderStock = orderStock;
    }


    public Set<TransactionBook> getTrans_company() {
        return trans_company;
    }

    public void setTrans_company(Set<TransactionBook> trans_company) {
        this.trans_company = trans_company;
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

    public Set<OrderStock> getOrderStock() {
        return orderStock;
    }

    public void setOrderStock(Set<OrderStock> orderStock) {
        this.orderStock = orderStock;
    }

    @Override
    public String toString() {
        return "TradingCompanies{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", marketCap='" + marketCap + '\'' +
                ", orderStock=" + orderStock +
                '}';
    }
}
