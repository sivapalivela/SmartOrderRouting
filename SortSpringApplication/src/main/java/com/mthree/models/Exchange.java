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

    @JsonManagedReference
    @OneToMany(mappedBy = "exchange", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TransactionBook> transactionBook = new HashSet<>();

    private double feeLadder;

    private double todayTransactionValue;

    public Exchange() {
    }

    public Exchange(String exchangeId, String exchangeName, Set<OrderStock> orderBook, Set<TransactionBook> transactionBook, double feeLadder, double todayTransactionValue) {
        this.exchangeId = exchangeId;
        this.exchangeName = exchangeName;
        this.orderBook = orderBook;
        this.transactionBook = transactionBook;
        this.feeLadder = feeLadder;
        this.todayTransactionValue = todayTransactionValue;
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

    public double getFeeLadder() {
        return feeLadder;
    }

    public void setFeeLadder(double feeLadder) {
        this.feeLadder = feeLadder;
    }

    public double getTodayTransactionValue() {
        return todayTransactionValue;
    }

    public void setTodayTransactionValue(double todayTransactionValue) {
        this.todayTransactionValue = todayTransactionValue;
    }

    public Set<TransactionBook> getTransactionBook() {
        return transactionBook;
    }

    public void setTransactionBook(Set<TransactionBook> transactionBook) {
        this.transactionBook = transactionBook;
    }

    public Set<OrderStock> getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(Set<OrderStock> orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "exchangeId='" + exchangeId + '\'' +
                ", exchangeName='" + exchangeName + '\'' +
                ", orderBook=" + orderBook +
                ", transactionBook=" + transactionBook +
                ", feeLadder=" + feeLadder +
                ", todayTransactionValue=" + todayTransactionValue +
                '}';
    }
}
