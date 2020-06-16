package com.mthree.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Consumers {

    @Id
    private String consumersId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobileNumber;
    private String location;
    private Long transactedAmountTillNow = 0L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "home_exchange", referencedColumnName = "exchangeId")
    private Exchange exchangeOfConsumers;

    @OneToMany(mappedBy = "consumers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderStock> ordersOfUser = new HashSet<>();

    @OneToMany(mappedBy = "consumers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TransactionBook> userTransactions = new HashSet<>();

    public Consumers() {
    }

    public Consumers(String consumersId, String firstName, String lastName, String email, String password, String mobileNumber, String location, Long transactedAmountTillNow, Exchange exchangeOfConsumers, Set<OrderStock> ordersOfUser, Set<TransactionBook> userTransactions) {
        this.consumersId = consumersId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.location = location;
        this.transactedAmountTillNow = transactedAmountTillNow;
        this.exchangeOfConsumers = exchangeOfConsumers;
        this.ordersOfUser = ordersOfUser;
        this.userTransactions = userTransactions;
    }

    public String getConsumersId() {
        return consumersId;
    }

    public void setConsumersId(String consumersId) {
        this.consumersId = consumersId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Long getTransactedAmountTillNow() {
        return transactedAmountTillNow;
    }

    public void setTransactedAmountTillNow(Long transactedAmountTillNow) {
        this.transactedAmountTillNow = transactedAmountTillNow;
    }

    public Exchange getExchangeOfConsumers() {
        return exchangeOfConsumers;
    }

    public void setExchangeOfConsumers(Exchange exchangeOfConsumers) {
        this.exchangeOfConsumers = exchangeOfConsumers;
    }

    public Set<OrderStock> getOrdersOfUser() {
        return ordersOfUser;
    }

    public void setOrdersOfUser(Set<OrderStock> ordersOfUser) {
        this.ordersOfUser = ordersOfUser;
    }

    public Set<TransactionBook> getUserTransactions() {
        return userTransactions;
    }

    public void setUserTransactions(Set<TransactionBook> userTransactions) {
        this.userTransactions = userTransactions;
    }

    @Override
    public String toString() {
        return "Consumers{" +
                "consumersId='" + consumersId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", location='" + location + '\'' +
                ", transactedAmountTillNow=" + transactedAmountTillNow +
                ", exchangeOfConsumers=" + exchangeOfConsumers +
                ", ordersOfUser=" + ordersOfUser +
                ", userTransactions=" + userTransactions +
                '}';
    }
}

