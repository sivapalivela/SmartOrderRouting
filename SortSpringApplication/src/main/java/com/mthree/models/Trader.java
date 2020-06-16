package com.mthree.models;

import javax.persistence.*;

@Entity
public class Trader {

    @Id
    private String traderId;
    private String traderName;
    private String email;
    private String password;
    private String mobileNumber;
    private String location;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trader_exchange", nullable = false, referencedColumnName = "exchangeId")
    private Exchange exchange;

    public Trader() {
    }

    public Trader(String traderId, String traderName, String email, String password, String mobileNumber, String location, Exchange exchange) {
        this.traderId = traderId;
        this.traderName = traderName;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.location = location;
        this.exchange = exchange;
    }

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "traderId='" + traderId + '\'' +
                ", traderName='" + traderName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", location='" + location + '\'' +
                ", exchange=" + exchange +
                '}';
    }
}
