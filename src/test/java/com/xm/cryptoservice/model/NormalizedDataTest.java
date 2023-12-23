package com.xm.cryptoservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NormalizedDataTest {

    private NormalizedData normalizedData;

    @BeforeEach
    public void setUp() {
        normalizedData = new NormalizedData();
    }

    @Test
    public void testSetAndGetSymbol() {
        String symbol = "BTC";
        normalizedData.setSymbol(symbol);
        assertEquals(symbol, normalizedData.getSymbol());
    }

    @Test
    public void testSetAndGetNormalizedRange() {
        BigDecimal normalizedRange = new BigDecimal("0.05");
        normalizedData.setNormalizedRange(normalizedRange);
        assertEquals(normalizedRange, normalizedData.getNormalizedRange());
    }
}