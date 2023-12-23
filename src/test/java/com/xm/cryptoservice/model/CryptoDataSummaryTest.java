package com.xm.cryptoservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CryptoDataSummaryTest {

    private CryptoDataSummary cryptoDataSummary;

    @BeforeEach
    public void setUp() {
        cryptoDataSummary = new CryptoDataSummary();
    }

    @Test
    public void testSetAndGetSymbol() {
        String symbol = "BTC";
        cryptoDataSummary.setSymbol(symbol);
        assertEquals(symbol, cryptoDataSummary.getSymbol());
    }

    @Test
    public void testSetAndGetMinPrice() {
        BigDecimal minPrice = new BigDecimal("30000.00");
        cryptoDataSummary.setMinPrice(minPrice);
        assertEquals(minPrice, cryptoDataSummary.getMinPrice());
    }

    @Test
    public void testSetAndGetMaxPrice() {
        BigDecimal maxPrice = new BigDecimal("60000.00");
        cryptoDataSummary.setMaxPrice(maxPrice);
        assertEquals(maxPrice, cryptoDataSummary.getMaxPrice());
    }

    @Test
    public void testSetAndGetOldestPrice() {
        BigDecimal oldestPrice = new BigDecimal("40000.00");
        cryptoDataSummary.setOldestPrice(oldestPrice);
        assertEquals(oldestPrice, cryptoDataSummary.getOldestPrice());
    }

    @Test
    public void testSetAndGetNewestPrice() {
        BigDecimal newestPrice = new BigDecimal("50000.00");
        cryptoDataSummary.setNewestPrice(newestPrice);
        assertEquals(newestPrice, cryptoDataSummary.getNewestPrice());
    }
}