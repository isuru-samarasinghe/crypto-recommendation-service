package com.xm.cryptoservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xm.cryptoservice.config.Constants;

/**
 * CryptoDataSummary class represents the summary data of a cryptocurrency.
 * It includes properties like symbol, minPrice, maxPrice, oldestPrice, and
 * newestPrice.
 */
@Entity
@Table(name = "crypto_data_summary")
public class CryptoDataSummary {

    @Id
    private String symbol;
    @Column(columnDefinition = "DECIMAL(" + Constants.DECIMAL_PRECISION + ", " + Constants.DECIMAL_SCALE + ")")
    private BigDecimal minPrice;
    @Column(columnDefinition = "DECIMAL(" + Constants.DECIMAL_PRECISION + ", " + Constants.DECIMAL_SCALE + ")")
    private BigDecimal maxPrice;
    @Column(columnDefinition = "DECIMAL(" + Constants.DECIMAL_PRECISION + ", " + Constants.DECIMAL_SCALE + ")")
    private BigDecimal oldestPrice;
    @Column(columnDefinition = "DECIMAL(" + Constants.DECIMAL_PRECISION + ", " + Constants.DECIMAL_SCALE + ")")
    private BigDecimal newestPrice;

    /**
     * Default constructor for the CryptoDataSummary class.
     */
    public CryptoDataSummary() {

    }

    /**
     * Constructor for the CryptoDataSummary class.
     * 
     * @param symbol      the symbol of the crypto
     * @param minPrice    the minimum price of the crypto
     * @param maxPrice    the maximum price of the crypto
     * @param oldestPrice the oldest price of the crypto
     * @param newestPrice the newest price of the crypto
     */
    public CryptoDataSummary(String symbol, BigDecimal minPrice, BigDecimal maxPrice, BigDecimal oldestPrice,
            BigDecimal newestPrice) {
        this.symbol = symbol;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.oldestPrice = oldestPrice;
        this.newestPrice = newestPrice;
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
     * This method returns the minimum price of the crypto.
     * 
     * @return the minimum price of the crypto
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * This method sets the minimum price of the crypto.
     * 
     * @param minPrice the minimum price to set
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * This method returns the maximum price of the crypto.
     * 
     * @return the maximum price of the crypto
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * This method sets the maximum price of the crypto.
     * 
     * @param maxPrice the maximum price to set
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * This method returns the oldest price of the crypto.
     * 
     * @return the oldest price of the crypto
     */
    public BigDecimal getOldestPrice() {
        return oldestPrice;
    }

    /**
     * This method sets the oldest price of the crypto.
     * 
     * @param oldestPrice the oldest price to set
     */
    public void setOldestPrice(BigDecimal oldestPrice) {
        this.oldestPrice = oldestPrice;
    }

    /**
     * This method returns the newest price of the crypto.
     * 
     * @return the newest price of the crypto
     */
    public BigDecimal getNewestPrice() {
        return newestPrice;
    }

    /**
     * This method sets the newest price of the crypto.
     * 
     * @param newestPrice the newest price to set
     */
    public void setNewestPrice(BigDecimal newestPrice) {
        this.newestPrice = newestPrice;
    }
}