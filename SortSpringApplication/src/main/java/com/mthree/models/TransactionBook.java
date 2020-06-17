package com.mthree.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class TransactionBook {

    @Id
    private String transId;
    private int buyerOrderId;
    private int sellerOrderId;
    private int numberOfShares;
    private double transactionAmount;
    private LocalDate timeStamp = java.time.LocalDate.now();;
    private String typeOfTransaction;
    private String buyerSideExchange;
    private String sellerSideExchange;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transactionsId", nullable = false)
    private Exchange exchange;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transactionsOfUser", nullable = false)
    private Consumers consumers;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transaction_company",nullable = false)
    private TradingCompanies company_name;



    public TransactionBook() {
    }

    public TransactionBook(String transId, int buyerOrderId, int sellerOrderId, int numberOfShares, double transactionAmount, LocalDate timeStamp, String typeOfTransaction, String buyerSideExchange, String sellerSideExchange, Exchange exchange, Consumers consumers) {
        this.transId = transId;
        this.buyerOrderId = buyerOrderId;
        this.sellerOrderId = sellerOrderId;
        this.numberOfShares = numberOfShares;
        this.transactionAmount = transactionAmount;
        this.timeStamp = timeStamp;
        this.typeOfTransaction = typeOfTransaction;
        this.buyerSideExchange = buyerSideExchange;
        this.sellerSideExchange = sellerSideExchange;
        this.exchange = exchange;
        this.consumers = consumers;
    }

    public TradingCompanies getCompany_name() {
        return company_name;
    }

    public void setCompany_name(TradingCompanies company_name) {
        this.company_name = company_name;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public int getBuyerOrderId() {
        return buyerOrderId;
    }

    public void setBuyerOrderId(int buyerOrderId) {
        this.buyerOrderId = buyerOrderId;
    }

    public int getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(int sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public String getBuyerSideExchange() {
        return buyerSideExchange;
    }

    public void setBuyerSideExchange(String buyerSideExchange) {
        this.buyerSideExchange = buyerSideExchange;
    }

    public String getSellerSideExchange() {
        return sellerSideExchange;
    }

    public void setSellerSideExchange(String sellerSideExchange) {
        this.sellerSideExchange = sellerSideExchange;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public Consumers getConsumers() {
        return consumers;
    }

    public void setConsumers(Consumers consumers) {
        this.consumers = consumers;
    }

    @Override
    public String toString() {
        return "TransactionBook{" +
                "transId='" + transId + '\'' +
                ", buyerOrderId=" + buyerOrderId +
                ", sellerOrderId=" + sellerOrderId +
                ", numberOfShares=" + numberOfShares +
                ", transactionAmount=" + transactionAmount +
                ", timeStamp=" + timeStamp +
                ", typeOfTransaction='" + typeOfTransaction + '\'' +
                ", buyerSideExchange='" + buyerSideExchange + '\'' +
                ", sellerSideExchange='" + sellerSideExchange + '\'' +
                ", exchange=" + exchange +
                ", consumers=" + consumers +
                '}';
    }
}
