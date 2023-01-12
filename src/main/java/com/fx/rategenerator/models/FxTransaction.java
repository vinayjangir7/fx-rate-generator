package com.fx.rategenerator.models;

import java.time.LocalDateTime;

public class FxTransaction {
    private String id;
    private String currencyPair;
    private double sellPrice;
    private double buyPrice;
    private LocalDateTime transactionTimeStamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public LocalDateTime getTransactionTimeStamp() {
        return transactionTimeStamp;
    }

    public void setTransactionTimeStamp(LocalDateTime transactionTimeStamp) {
        this.transactionTimeStamp = transactionTimeStamp;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", currencyPair='" + currencyPair + '\'' +
                ", sellPrice=" + sellPrice +
                ", buyPrice=" + buyPrice +
                ", transactionTimeStamp=" + transactionTimeStamp +
                '}';
    }
}
