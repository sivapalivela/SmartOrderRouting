package com.mthree.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class DarkPoolOrderBook {
    @Id
    private int orderId;
    private int numberOfShares;
    private String orderExchangeId;
    private String typeOfOrder;
    private String Company;
    private double price;
    private LocalDate timeStamp = java.time.LocalDate.now();
    private String orderStatus;
    private String user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "darkPool", nullable = false)
    private Sort sort;



    public DarkPoolOrderBook() {
    }

    public DarkPoolOrderBook(int orderId, int numberOfShares, String orderExchangeId, String typeOfOrder, String company, double price, LocalDate timeStamp, String orderStatus, String user, Sort sort) {
        this.orderId = orderId;
        this.numberOfShares = numberOfShares;
        this.orderExchangeId = orderExchangeId;
        this.typeOfOrder = typeOfOrder;
        Company = company;
        this.price = price;
        this.timeStamp = timeStamp;
        this.orderStatus = orderStatus;
        this.user = user;
        this.sort = sort;
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

    public String getOrderExchangeId() {
        return orderExchangeId;
    }

    public void setOrderExchangeId(String orderExchangeId) {
        this.orderExchangeId = orderExchangeId;
    }

    public String getTypeOfOrder() {
        return typeOfOrder;
    }

    public void setTypeOfOrder(String typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "DarkPoolOrderBook{" +
                "orderId=" + orderId +
                ", numberOfShares=" + numberOfShares +
                ", orderExchangeId='" + orderExchangeId + '\'' +
                ", typeOfOrder='" + typeOfOrder + '\'' +
                ", Company='" + Company + '\'' +
                ", price=" + price +
                ", timeStamp=" + timeStamp +
                ", orderStatus='" + orderStatus + '\'' +
                ", user='" + user + '\'' +
                ", sort=" + sort +
                '}';
    }
}
