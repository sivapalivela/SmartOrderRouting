package com.mthree.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

@Entity
public class Exchange {
    @Id
    private String exchangeId;

    private String exchangeName;

    @JsonManagedReference
    @OneToMany(mappedBy = "exchange", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderStock> orderBook = new HashSet<>();

    @OneToMany(mappedBy = "exchange", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TransactionBook> transactionBook = new HashSet<>();

    private double overallTransactionValue;

    @OneToMany(mappedBy = "exchangeOfConsumers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Consumers> usersOfExchange = new HashSet<>();

    @OneToMany(mappedBy = "exchange",fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private Set<Trader> tradersOfExchange = new HashSet<>();

    private double feeConstant;

    private double bulkFeeConstant;


    public Exchange() {
    }

    public Exchange(String exchangeId, String exchangeName, Set<OrderStock> orderBook, Set<TransactionBook> transactionBook, double overallTransactionValue, Set<Consumers> usersOfExchange, Set<Trader> tradersOfExchange, double feeConstant, double bulkFeeConstant) {
        this.exchangeId = exchangeId;
        this.exchangeName = exchangeName;
        this.orderBook = orderBook;
        this.transactionBook = transactionBook;
        this.overallTransactionValue = overallTransactionValue;
        this.usersOfExchange = usersOfExchange;
        this.tradersOfExchange = tradersOfExchange;
        this.feeConstant = feeConstant;
        this.bulkFeeConstant = bulkFeeConstant;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public Set<OrderStock> getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(Set<OrderStock> orderBook) {
        this.orderBook = orderBook;
    }

    public Set<TransactionBook> getTransactionBook() {
        return transactionBook;
    }

    public void setTransactionBook(Set<TransactionBook> transactionBook) {
        this.transactionBook = transactionBook;
    }

    public double getOverallTransactionValue() {
        return overallTransactionValue;
    }

    public void setOverallTransactionValue(double overallTransactionValue) {
        this.overallTransactionValue = overallTransactionValue;
    }

    public Set<Consumers> getUsersOfExchange() {
        return usersOfExchange;
    }

    public void setUsersOfExchange(Set<Consumers> usersOfExchange) {
        this.usersOfExchange = usersOfExchange;
    }

    public Set<Trader> getTradersOfExchange() {
        return tradersOfExchange;
    }

    public void setTradersOfExchange(Set<Trader> tradersOfExchange) {
        this.tradersOfExchange = tradersOfExchange;
    }

    public double getFeeConstant() {
        return feeConstant;
    }

    public void setFeeConstant(double feeConstant) {
        this.feeConstant = feeConstant;
    }

    public double getBulkFeeConstant() {
        return bulkFeeConstant;
    }

    public void setBulkFeeConstant(double bulkFeeConstant) {
        this.bulkFeeConstant = bulkFeeConstant;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "exchangeId='" + exchangeId + '\'' +
                ", exchangeName='" + exchangeName + '\'' +
                ", orderBook=" + orderBook +
                ", transactionBook=" + transactionBook +
                ", overallTransactionValue=" + overallTransactionValue +
                ", usersOfExchange=" + usersOfExchange +
                ", tradersOfExchange=" + tradersOfExchange +
                ", feeConstant=" + feeConstant +
                ", bulkFeeConstant=" + bulkFeeConstant +
                '}';
    }
}
