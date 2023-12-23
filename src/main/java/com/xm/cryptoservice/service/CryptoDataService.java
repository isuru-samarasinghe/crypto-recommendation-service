package com.xm.cryptoservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xm.cryptoservice.config.Constants;
import com.xm.cryptoservice.model.CryptoData;
import com.xm.cryptoservice.model.CryptoDataSummary;
import com.xm.cryptoservice.model.NormalizedData;
import com.xm.cryptoservice.repository.CryptoDataRepository;
import com.xm.cryptoservice.repository.CryptoDataSummaryRepository;
import com.xm.cryptoservice.repository.NormalizedDataRepository;

@Service
public class CryptoDataService {

    @Autowired
    private CryptoDataRepository cryptoDataRepository;

    @Autowired
    private CryptoDataSummaryRepository cryptoDataSummaryRepository;

    @Autowired
    private NormalizedDataRepository normalizedDataRepository;

    public List<String> getAllSymbols() {
        return cryptoDataRepository.findAllSymbols();
    }

    @Transactional
    public void saveCryptoData(List<CryptoData> cryptoDataList) {
        cryptoDataRepository.deleteAll();
        cryptoDataRepository.saveAll(cryptoDataList);
    }

    @Transactional
    public void saveSummaries() {
        List<String> symbols = getAllSymbols();

        List<CryptoDataSummary> summaries = symbols.stream()
                .map(this::calculatePriceSummary)
                .collect(Collectors.toList());

        cryptoDataSummaryRepository.deleteAll();
        cryptoDataSummaryRepository.saveAll(summaries);
    }

    private CryptoDataSummary calculatePriceSummary(String symbol) {

        BigDecimal minPrice = cryptoDataRepository.findMinPriceBySymbol(symbol);
        BigDecimal maxPrice = cryptoDataRepository.findMaxPriceBySymbol(symbol);
        BigDecimal oldestPrice = cryptoDataRepository.findOldestDataBySymbol(symbol).orElse(null).getPrice();
        BigDecimal newestPrice = cryptoDataRepository.findLatestDataBySymbol(symbol).orElse(null).getPrice();

        return new CryptoDataSummary(symbol, minPrice, maxPrice, oldestPrice, newestPrice);
    }

    public CryptoDataSummary getPriceSummary(String symbol) {
        return cryptoDataSummaryRepository.findById(symbol).orElse(null);
    }

    @Transactional
    public void saveNormalizedPriceRange() {
        List<NormalizedData> normalizedDataList = calculateNormalizedPriceRange();
        normalizedDataRepository.deleteAll();
        normalizedDataRepository.saveAll(normalizedDataList);
    }

    private List<NormalizedData> calculateNormalizedPriceRange() {
        List<String> symbols = getAllSymbols();

        return symbols.stream()
                .map(symbol -> {
                    List<CryptoData> cryptoDataList = cryptoDataRepository.findDataBySymbol(symbol);
                    BigDecimal normalizedPriceRange = calculateNormalizedPriceRange(cryptoDataList);
                    return new NormalizedData(symbol, normalizedPriceRange);
                })
                .collect(Collectors.toList());
    }

    public List<NormalizedData> getAllNormalizedData() {
        return normalizedDataRepository.findAllOrderByNormalizedRangeDesc();
    }

    public NormalizedData getHighestNormalizedRangeForSpecificDay(LocalDate date) {
        Long startTimestamp = date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        Long endTimestamp = date.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        Map<String, List<CryptoData>> groupedRecords = cryptoDataRepository
                .findAllWithinTimestamp(startTimestamp, endTimestamp)
                .stream()
                .collect(Collectors.groupingBy(CryptoData::getSymbol));
        NormalizedData maxNormalizedData = groupedRecords.entrySet().stream()
                .map(entry -> {
                    String symbol = entry.getKey();
                    List<CryptoData> cryptoDataList = entry.getValue();
                    BigDecimal normalizedPriceRange = calculateNormalizedPriceRange(cryptoDataList);
                    return new NormalizedData(symbol, normalizedPriceRange);
                })
                .max(Comparator.comparing(NormalizedData::getNormalizedRange))
                .orElse(null);

        return maxNormalizedData;
    }

    private BigDecimal calculateNormalizedPriceRange(List<CryptoData> cryptoDataList) {
        BigDecimal minPrice = cryptoDataList.stream()
                .min(Comparator.comparing(CryptoData::getPrice))
                .get()
                .getPrice();

        BigDecimal maxPrice = cryptoDataList.stream()
                .max(Comparator.comparing(CryptoData::getPrice))
                .get()
                .getPrice();
        return maxPrice.subtract(minPrice).divide(minPrice, Constants.DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
    }

}
