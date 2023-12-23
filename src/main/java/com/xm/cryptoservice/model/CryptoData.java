package com.xm.cryptoservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xm.cryptoservice.config.Constants;

/**
 * CryptoData class represents the data of a cryptocurrency.
 * It includes properties like id, timestamp, symbol, and price.
 */
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

    /**
     * Default constructor for the CryptoData class.
     */
    public CryptoData() {
    }

    /**
     * Constructor for the CryptoData class.
     * 
     * @param timestamp the timestamp of the crypto data
     * @param symbol    the symbol of the crypto
     * @param price     the price of the crypto
     */
    public CryptoData(Long timestamp, String symbol, BigDecimal price) {
        this.id = generateId();
        this.timestamp = timestamp;
        this.symbol = symbol;
        this.price = price;
    }

    /**
     * This method generates the id for the crypto data.
     * 
     * @return the generated id
     */
    private String generateId() {
        return symbol + "-" + timestamp;
    }

    /**
     * This method returns the id of the crypto data.
     * 
     * @return the id of the crypto data
     */
    public String getId() {
        if (id == null) {
            id = generateId();
        }
        return id;
    }

    /**
     * This method sets the id of the crypto data.
     * 
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method returns the timestamp of the crypto data.
     * 
     * @return the timestamp of the crypto data
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * This method sets the timestamp of the crypto data.
     * 
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * This method returns the symbol of the crypto.
     * 
     * @return the symbol of the crypto
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * This method sets the symbol of the crypto.
     * 
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * This method returns the price of the crypto.
     * 
     * @return the price of the crypto
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method sets the price of the crypto.
     * 
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method returns a string representation of the crypto data.
     * 
     * @return a string representation of the crypto data
     */
    @Override
    public String toString() {
        return "CryptoData{" +
                "timestamp=" + timestamp +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}