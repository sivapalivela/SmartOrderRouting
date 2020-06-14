package com.mthree.models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Sort {
    @Id
    private int id;
//    TODO: Discuss sort orderBooks
    @OneToMany(mappedBy = "exchange", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderStock> orderBook;
    @OneToMany(mappedBy = "exchange", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Map<String,OrderStock> transactions;

    public Sort() {
    }

    public Sort(int id, Set<OrderStock> orderBook, Map<String, OrderStock> transactions) {
        this.id = id;
        this.orderBook = orderBook;
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<OrderStock> getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(Set<OrderStock> orderBook) {
        this.orderBook = orderBook;
    }

    public Map<String, OrderStock> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<String, OrderStock> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "id='" + id + '\'' +
                ", orderBook=" + orderBook +
                ", transactions=" + transactions +
                '}';
    }
}
