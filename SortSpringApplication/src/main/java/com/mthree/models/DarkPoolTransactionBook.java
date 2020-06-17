package com.mthree.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DarkPoolTransactionBook {
    @Id
    private String transId;
    private int buyerOrderId;
    private int sellerOrderId;
    private int numberOfShares;
    private double transactionAmount;
    private Date timeStamp;
    private String typeOfTransaction;
    private String buyerSideExchange;
    private String sellerSideExchange;
    private String consumer;
    private String trading_company;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "darkPoolTransactions", nullable = false)
    private Sort sort;

    public DarkPoolTransactionBook() {
    }

    public DarkPoolTransactionBook(String transId, int buyerOrderId, int sellerOrderId, int numberOfShares, double transactionAmount, Date timeStamp, String typeOfTransaction, String buyerSideExchange, String sellerSideExchange, String consumer, String trading_company, Sort sort) {
        this.transId = transId;
        this.buyerOrderId = buyerOrderId;
        this.sellerOrderId = sellerOrderId;
        this.numberOfShares = numberOfShares;
        this.transactionAmount = transactionAmount;
        this.timeStamp = timeStamp;
        this.typeOfTransaction = typeOfTransaction;
        this.buyerSideExchange = buyerSideExchange;
        this.sellerSideExchange = sellerSideExchange;
        this.consumer = consumer;
        this.trading_company = trading_company;
        this.sort = sort;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
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

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getTrading_company() {
        return trading_company;
    }

    public void setTrading_company(String trading_company) {
        this.trading_company = trading_company;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "DarkPoolTransactionBook{" +
                "transId='" + transId + '\'' +
                ", buyerOrderId=" + buyerOrderId +
                ", sellerOrderId=" + sellerOrderId +
                ", numberOfShares=" + numberOfShares +
                ", transactionAmount=" + transactionAmount +
                ", timeStamp=" + timeStamp +
                ", typeOfTransaction='" + typeOfTransaction + '\'' +
                ", buyerSideExchange='" + buyerSideExchange + '\'' +
                ", sellerSideExchange='" + sellerSideExchange + '\'' +
                ", consumer='" + consumer + '\'' +
                ", trading_company='" + trading_company + '\'' +
                ", sort=" + sort +
                '}';
    }
}
