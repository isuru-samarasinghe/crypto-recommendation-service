package com.xm.cryptoservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xm.cryptoservice.config.Constants;

@Entity
@Table(name = "crypto_data")
public class CryptoData {

    @Id
    @JsonIgnore
    private String id;
    private Long timestamp;
    private String symbol;
    @Column(columnDefinition = "DECIMAL(" + Constants.DECIMAL_PRECISION + ", " + Constants.DECIMAL_SCALE + ")")
    private BigDecimal price;

    public CryptoData() {
    }

    public CryptoData(Long timestamp, String symbol, BigDecimal price) {
        this.id = generateId();
        this.timestamp = timestamp;
        this.symbol = symbol;
        this.price = price;
    }

    private String generateId() {
        return symbol + "-" + timestamp;
    }

    public String getId() {
        if (id == null) {
            id = generateId();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CryptoData{" +
                "timestamp=" + timestamp +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}