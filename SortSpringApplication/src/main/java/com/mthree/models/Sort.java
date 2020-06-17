package com.mthree.models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Sort {
    @Id
    private int id;

    @OneToMany(mappedBy = "sort", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DarkPoolOrderBook> orderBook = new HashSet<>();

    @OneToMany(mappedBy = "sort", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DarkPoolTransactionBook> transactions = new HashSet<>();


    public Sort() {
    }

    public Sort(int id, Set<DarkPoolOrderBook> orderBook, Set<DarkPoolTransactionBook> transactions) {
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

    public Set<DarkPoolOrderBook> getOrderBook() {
        return orderBook;
    }

    public Set<DarkPoolTransactionBook> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<DarkPoolTransactionBook> transactions) {
        this.transactions = transactions;
    }

    public void setOrderBook(Set<DarkPoolOrderBook> orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "id=" + id +
                ", orderBook=" + orderBook +
                ", transactions=" + transactions +
                '}';
    }
}
