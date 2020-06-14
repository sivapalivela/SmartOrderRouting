package com.mthree.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TradingCompanies {
    @Id
    private String companyId;
    private String companyName;

    @JsonBackReference
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<OrderStock> orderStock;

    public TradingCompanies() {
    }

    public TradingCompanies(String companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
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

    @Override
    public String toString() {
        return "TradingCompanies{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
