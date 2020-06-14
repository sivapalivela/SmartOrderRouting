package com.mthree.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OrderStock {
    @Id
    @GeneratedValue
    private int orderId;
    private int numberOfShares;
    private String orderExchangeId;
    private String typeOfOrder;
    private double price;
    private Date timeStamp = new Date();
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exchangeId", nullable = false)
    private Exchange exchange;

    public OrderStock() {
    }

    public OrderStock(int numberOfShares, String orderExchangeId, String typeOfOrder, double price,Date timeStamp) {
        this.numberOfShares = numberOfShares;
        this.orderExchangeId = orderExchangeId;
        this.typeOfOrder = typeOfOrder;
        this.price = price;
        this.timeStamp = timeStamp;
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

    public String getorderExchangeId() {
        return orderExchangeId;
    }

    public void setorderExchangeId(String orderExchangeId) {
        this.orderExchangeId = orderExchangeId;
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

    @Override
    public String toString() {
        return "OrderStock{" +
                "orderId=" + orderId +
                ", numberOfShares=" + numberOfShares +
                ", orderExchangeId='" + orderExchangeId + '\'' +
                ", typeOfOrder='" + typeOfOrder + '\'' +
                ", price=" + price +
                ", timeStamp=" + timeStamp +
                ", exchange=" + exchange +
                '}';
    }
}
