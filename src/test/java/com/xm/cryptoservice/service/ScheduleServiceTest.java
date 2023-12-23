package com.xm.cryptoservice.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.xm.cryptoservice.component.CSVDataReader;
import com.xm.cryptoservice.model.CryptoData;

public class ScheduleServiceTest {

    @InjectMocks
    private ScheduleService scheduleService;

    @Mock
    private CryptoDataService cryptoDataService;

    @Mock
    private CSVDataReader csvDataReader;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testReadCsvAndStoreInDb() throws IOException {
        List<CryptoData> mockData = Collections.emptyList();
        when(csvDataReader.readCryptoData()).thenReturn(mockData);

        scheduleService.readCsvAndStoreInDb();

        verify(cryptoDataService, times(1)).saveCryptoData(mockData);
        verify(cryptoDataService, times(1)).saveSummaries();
        verify(cryptoDataService, times(1)).saveNormalizedPriceRange();
    }
}