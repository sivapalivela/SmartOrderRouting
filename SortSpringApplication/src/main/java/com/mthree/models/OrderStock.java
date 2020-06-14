package com.mthree.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OrderStock {
    @Id
    @GeneratedValue
    private int orderId;
    private int numberOfShares;
    private String orderExchangeId;
    private String typeOfOrder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "com_id", referencedColumnName = "companyId")
    private TradingCompanies company;

    private double price;
    private Date timeStamp = new Date();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exchangeId", nullable = false)
    private Exchange exchange;

    private String orderStatus;

    public OrderStock() {
    }

    public OrderStock(int orderId, int numberOfShares, String orderExchangeId, String typeOfOrder, TradingCompanies company, double price, Date timeStamp, Exchange exchange, String orderStatus) {
        this.orderId = orderId;
        this.numberOfShares = numberOfShares;
        this.orderExchangeId = orderExchangeId;
        this.typeOfOrder = typeOfOrder;
        this.company = company;
        this.price = price;
        this.timeStamp = timeStamp;
        this.exchange = exchange;
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public String getTypeOfOrder() {
        return typeOfOrder;
    }

    public void setTypeOfOrder(String typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getOrderExchangeId() {
        return orderExchangeId;
    }

    public void setOrderExchangeId(String orderExchangeId) {
        this.orderExchangeId = orderExchangeId;
    }

    public TradingCompanies getCompany() {
        return company;
    }

    public void setCompany(TradingCompanies company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "OrderStock{" +
                "orderId=" + orderId +
                ", numberOfShares=" + numberOfShares +
                ", orderExchangeId='" + orderExchangeId + '\'' +
                ", typeOfOrder='" + typeOfOrder + '\'' +
                ", company=" + company +
                ", price=" + price +
                ", timeStamp=" + timeStamp +
                ", exchange=" + exchange +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
