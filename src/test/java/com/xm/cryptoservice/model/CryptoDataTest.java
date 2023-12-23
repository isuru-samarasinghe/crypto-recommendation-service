package com.xm.cryptoservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CryptoDataTest {

    private CryptoData cryptoData;

    @BeforeEach
    public void setUp() {
        cryptoData = new CryptoData();
    }

    @Test
    public void testSetAndGetId() {
        String id = "BTC-1641009600000";
        cryptoData.setId(id);
        assertEquals(id, cryptoData.getId());
    }

    @Test
    public void testGenerateId() {
        Long timestamp = 1641009600000L;
        String symbol = "BTC";
        cryptoData.setTimestamp(timestamp);
        cryptoData.setSymbol(symbol);
        assertEquals("BTC-1641009600000", cryptoData.getId());
    }

    @Test
    public void testSetAndGetTimestamp() {
        Long timestamp = 1641009600000L;
        cryptoData.setTimestamp(timestamp);
        assertEquals(timestamp, cryptoData.getTimestamp());
    }

    @Test
    public void testSetAndGetSymbol() {
        String symbol = "BTC";
        cryptoData.setSymbol(symbol);
        assertEquals(symbol, cryptoData.getSymbol());
    }

    @Test
    public void testSetAndGetPrice() {
        BigDecimal price = new BigDecimal("46813.21");
        cryptoData.setPrice(price);
        assertEquals(price, cryptoData.getPrice());
    }

    @Test
    public void testToString() {
        Long timestamp = 1641009600000L;
        String symbol = "BTC";
        BigDecimal price = new BigDecimal("46813.21");
        cryptoData.setTimestamp(timestamp);
        cryptoData.setSymbol(symbol);
        cryptoData.setPrice(price);

        String expectedString = "CryptoData{" +
                "timestamp=" + timestamp +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';

        assertEquals(expectedString, cryptoData.toString());
    }
}