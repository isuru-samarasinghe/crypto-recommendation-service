package com.xm.cryptoservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xm.cryptoservice.model.CryptoData;
import com.xm.cryptoservice.model.CryptoDataSummary;
import com.xm.cryptoservice.model.NormalizedData;
import com.xm.cryptoservice.repository.CryptoDataRepository;
import com.xm.cryptoservice.repository.CryptoDataSummaryRepository;
import com.xm.cryptoservice.repository.NormalizedDataRepository;

@ExtendWith(MockitoExtension.class)
public class CryptoDataServiceTest {

    @InjectMocks
    private CryptoDataService cryptoDataService;

    @Mock
    private CryptoDataRepository cryptoDataRepository;

    @Mock
    private CryptoDataSummaryRepository cryptoDataSummaryRepository;

    @Mock
    private NormalizedDataRepository normalizedDataRepository;

    @Test
    public void testGetAllSymbols() {
        when(cryptoDataRepository.findAllSymbols()).thenReturn(Arrays.asList("BTC", "ETH", "XRP"));
        List<String> result = cryptoDataService.getAllSymbols();
        assertEquals(3, result.size());
        assertEquals("BTC", result.get(0));
        assertEquals("ETH", result.get(1));
        assertEquals("XRP", result.get(2));
    }

    @Test
    public void testGetPriceSummary() {
        String symbol = "BTC";
        CryptoDataSummary expectedSummary = new CryptoDataSummary();
        when(cryptoDataSummaryRepository.findById(symbol)).thenReturn(Optional.of(expectedSummary));

        CryptoDataSummary result = cryptoDataService.getPriceSummary(symbol);

        assertEquals(expectedSummary, result);
    }

    @Test
    public void testGetAllNormalizedData() {
        NormalizedData normalizedData = new NormalizedData("BTC", BigDecimal.ONE);
        when(normalizedDataRepository.findAllOrderByNormalizedRangeDesc()).thenReturn(Arrays.asList(normalizedData));

        List<NormalizedData> result = cryptoDataService.getAllNormalizedData();

        assertEquals(1, result.size());
        assertEquals(normalizedData, result.get(0));
    }

    @Test
    public void testSaveCryptoData() {
        List<CryptoData> cryptoDataList = Arrays
                .asList(new CryptoData(1641009600000L, "BTC", new BigDecimal("46813.21")));
        cryptoDataService.saveCryptoData(cryptoDataList);
        verify(cryptoDataRepository, times(1)).deleteAll();
        verify(cryptoDataRepository, times(1)).saveAll(cryptoDataList);
    }

    @Test
    public void testSaveSummaries() {
        when(cryptoDataRepository.findAllSymbols()).thenReturn(Arrays.asList("BTC"));
        when(cryptoDataRepository.findMinPriceBySymbol("BTC")).thenReturn(new BigDecimal("30000.00"));
        when(cryptoDataRepository.findMaxPriceBySymbol("BTC")).thenReturn(new BigDecimal("60000.00"));
        CryptoData oldestData = new CryptoData(1641009600000L, "BTC", new BigDecimal("30000.00"));
        when(cryptoDataRepository.findOldestDataBySymbol("BTC")).thenReturn(Optional.of(oldestData));
        CryptoData newestData = new CryptoData(1641009600000L, "BTC", new BigDecimal("60000.00"));
        when(cryptoDataRepository.findLatestDataBySymbol("BTC")).thenReturn(Optional.of(newestData));
        cryptoDataService.saveSummaries();
        verify(cryptoDataSummaryRepository, times(1)).deleteAll();
        verify(cryptoDataSummaryRepository, times(1)).saveAll(any());
    }

    @Test
    public void testSaveNormalizedPriceRange() {
        when(cryptoDataRepository.findAllSymbols()).thenReturn(Arrays.asList("BTC"));
        when(cryptoDataRepository.findDataBySymbol("BTC")).thenReturn(Arrays.asList(
                new CryptoData(1641009600000L, "BTC", new BigDecimal("30000.00")),
                new CryptoData(1641009600000L, "BTC", new BigDecimal("60000.00"))));
        cryptoDataService.saveNormalizedPriceRange();
        verify(normalizedDataRepository, times(1)).deleteAll();
        verify(normalizedDataRepository, times(1)).saveAll(any());
    }

    @Test
    public void testGetHighestNormalizedRangeForSpecificDay() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        when(cryptoDataRepository.findAllWithinTimestamp(anyLong(), anyLong())).thenReturn(Arrays.asList(
                new CryptoData(1641009600000L, "BTC", new BigDecimal("30000.00")),
                new CryptoData(1641009600000L, "BTC", new BigDecimal("60000.00"))));
        NormalizedData result = cryptoDataService.getHighestNormalizedRangeForSpecificDay(date);
        assertEquals(new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP),
                result.getNormalizedRange().setScale(2, RoundingMode.HALF_UP));
    }
}