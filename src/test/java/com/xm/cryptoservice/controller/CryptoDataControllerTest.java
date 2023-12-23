package com.xm.cryptoservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.xm.cryptoservice.model.CryptoDataSummary;
import com.xm.cryptoservice.model.NormalizedData;
import com.xm.cryptoservice.service.CryptoDataService;

public class CryptoDataControllerTest {

    @InjectMocks
    private CryptoDataController cryptoDataController;

    @Mock
    private CryptoDataService cryptoDataService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSortedCryptoData() {
        List<NormalizedData> expectedData = Collections.singletonList(new NormalizedData());
        when(cryptoDataService.getAllNormalizedData()).thenReturn(expectedData);

        ResponseEntity<List<NormalizedData>> response = cryptoDataController.getSortedCryptoData();

        assertEquals(expectedData, response.getBody());
    }

    @Test
    public void testGetSummaryBySymbol() {
        String symbol = "BTC";
        CryptoDataSummary expectedSummary = new CryptoDataSummary();
        when(cryptoDataService.getPriceSummary(symbol)).thenReturn(expectedSummary);

        ResponseEntity<CryptoDataSummary> response = cryptoDataController.getSummaryBySymbol(symbol);

        assertEquals(expectedSummary, response.getBody());
    }

    @Test
    public void testGetSummaryBySymbolNotFound() {
        String symbol = "BTC";
        when(cryptoDataService.getPriceSummary(symbol)).thenReturn(null);

        ResponseEntity<CryptoDataSummary> response = cryptoDataController.getSummaryBySymbol(symbol);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetHighestNormalizedDataForDate() {
        String date = "2022-01-01";
        NormalizedData expectedData = new NormalizedData();
        when(cryptoDataService.getHighestNormalizedRangeForSpecificDay(LocalDate.parse(date))).thenReturn(expectedData);

        ResponseEntity<NormalizedData> response = cryptoDataController.getHighestNormalizedDataForDate(date);

        assertEquals(expectedData, response.getBody());
    }

    @Test
    public void testGetHighestNormalizedDataForDateNotFound() {
        String date = "2022-01-01";
        when(cryptoDataService.getHighestNormalizedRangeForSpecificDay(LocalDate.parse(date))).thenReturn(null);

        ResponseEntity<NormalizedData> response = cryptoDataController.getHighestNormalizedDataForDate(date);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetHighestNormalizedDataForDateInvalidFormat() {
        String date = "invalid-date";

        assertThrows(ResponseStatusException.class, () -> cryptoDataController.getHighestNormalizedDataForDate(date));
    }
}