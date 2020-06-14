package com.mthree.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TransactionBook {

    @Id
    private String transId;
    private int buyerOrderId;
    private int sellerOrderId;
    private int numberOfShares;
    private double transactionAmount;
    private Date timeStamp = new Date();
    private String typeOfTransaction;
    private String buyerSideExchange;
    private String sellerSideExchange;
    //TODO Dark Pool Variables
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transactionId", nullable = false)
    private Exchange exchange;

    public TransactionBook() {
    }

    public TransactionBook(String transId, int buyerOrderId, int sellerOrderId, int numberOfShares, double transactionAmount, Date timeStamp, String typeOfTransaction, String buyerSideExchange, String sellerSideExchange, Exchange exchange) {
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
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
                '}';
    }
}
