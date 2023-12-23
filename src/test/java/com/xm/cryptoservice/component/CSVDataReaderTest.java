package com.xm.cryptoservice.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xm.cryptoservice.model.CryptoData;

public class CSVDataReaderTest {

    private CSVDataReader csvDataReader;

    @BeforeEach
    public void setup() {
        csvDataReader = new CSVDataReader();
    }

    @Test
    public void testReadCryptoData() throws IOException {
        List<CryptoData> cryptoDataList = csvDataReader.readCryptoData();

        assertNotNull(cryptoDataList);
        assertFalse(cryptoDataList.isEmpty());

        CryptoData cryptoData = cryptoDataList.get(0);
        // Add assertions here based on the expected content of the first row in your
        // CSV file
        assertEquals(1641009600000L, cryptoData.getTimestamp());
        assertEquals("BTC", cryptoData.getSymbol());
        assertEquals(46813.21, cryptoData.getPrice().doubleValue(), 0.01);
    }
}