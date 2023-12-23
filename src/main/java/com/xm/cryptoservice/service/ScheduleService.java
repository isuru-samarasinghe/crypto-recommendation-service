package com.xm.cryptoservice.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xm.cryptoservice.component.CSVDataReader;
import com.xm.cryptoservice.model.CryptoData;

@Component
public class ScheduleService {

    @Autowired
    private CryptoDataService cryptoDataService;

    @Autowired
    private CSVDataReader csvDataReader;

    @PostConstruct
    public void onStartup() throws IOException {
        readCsvAndStoreInDb();
    }

    @Scheduled(cron = "0 0 * * * *") // This runs the job every day at midnight
    public void readCsvAndStoreInDb() throws IOException {
        List<CryptoData> cryptoDataList = csvDataReader.readCryptoData();
        cryptoDataService.saveCryptoData(cryptoDataList);
        // After saving all the data, do calculations
        cryptoDataService.saveSummaries();
        cryptoDataService.saveNormalizedPriceRange();
    }
}