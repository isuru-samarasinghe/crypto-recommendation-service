package com.xm.cryptoservice.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xm.cryptoservice.component.CSVDataReader;
import com.xm.cryptoservice.model.CryptoData;

/**
 * ScheduleService class is responsible for scheduling tasks related to
 * cryptocurrency data.
 * It includes methods for reading CSV data and storing it in the database.
 */
@Component
public class ScheduleService {

    @Autowired
    private CryptoDataService cryptoDataService;

    @Autowired
    private CSVDataReader csvDataReader;

    /**
     * This method is called once on startup and reads CSV data and stores it in the
     * database.
     * 
     * @throws IOException if an I/O error occurs
     */
    @PostConstruct
    public void onStartup() throws IOException {
        readCsvAndStoreInDb();
    }

    /**
     * This method is scheduled to run every day at midnight. It reads CSV data,
     * stores it in the database,
     * and then performs calculations on the data.
     * 
     * @throws IOException if an I/O error occurs
     */
    @Scheduled(cron = "0 0 * * * *") // This runs the job every day at midnight
    public void readCsvAndStoreInDb() throws IOException {
        List<CryptoData> cryptoDataList = csvDataReader.readCryptoData();
        cryptoDataService.saveCryptoData(cryptoDataList);
        // After saving all the data, do calculations
        cryptoDataService.saveSummaries();
        cryptoDataService.saveNormalizedPriceRange();
    }
}