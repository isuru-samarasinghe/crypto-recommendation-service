package com.xm.cryptoservice.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.xm.cryptoservice.model.CryptoDataSummary;
import com.xm.cryptoservice.model.NormalizedData;
import com.xm.cryptoservice.service.CryptoDataService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * CryptoDataController class is responsible for handling HTTP requests related
 * to crypto data.
 * It provides endpoints for retrieving sorted crypto data, summary by symbol,
 * and highest normalized data for a specific date.
 */
@RestController
@RequestMapping("/api/v1/crypto")
public class CryptoDataController {

    @Autowired
    private CryptoDataService cryptoDataService;

    /**
     * This method handles GET requests to retrieve a descending sorted list of all
     * the cryptos, comparing the normalized range.
     * 
     * @return a ResponseEntity with the list of NormalizedData
     */
    @ApiOperation(value = "Returns a descending sorted list of all the cryptos, comparing the normalized range (i.e. (max-min)/min)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data."),
            @ApiResponse(code = 404, message = "No crypto was found.")
    })
    @GetMapping("/")
    public ResponseEntity<List<NormalizedData>> getSortedCryptoData() {
        return ResponseEntity.ok(cryptoDataService.getAllNormalizedData());
    }

    /**
     * This method handles GET requests to retrieve the oldest/newest/min/max values
     * for a requested crypto.
     * 
     * @param symbol the symbol of the crypto for which data is requested
     * @return a ResponseEntity with the CryptoDataSummary
     */
    @ApiOperation(value = "Returns the oldest/newest/min/max values for a requested crypto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data."),
            @ApiResponse(code = 404, message = "No crypto was found for the given symbol.")
    })
    @GetMapping("/{symbol}")
    public ResponseEntity<CryptoDataSummary> getSummaryBySymbol(
            @ApiParam(value = "Symbol of the crypto for which data is requested (ex: BTC)", required = true) @PathVariable String symbol) {
        return Optional.ofNullable(cryptoDataService.getPriceSummary(symbol))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * This method handles GET requests to retrieve the crypto with the highest
     * normalized range for a specific day.
     * 
     * @param date the date for which the data is requested
     * @return a ResponseEntity with the NormalizedData
     */
    @ApiOperation(value = "Returns the crypto with the highest normalized range for a specific day")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data."),
            @ApiResponse(code = 400, message = "Invalid date format. Date should be in 'yyyy-MM-dd' format"),
            @ApiResponse(code = 404, message = "No data was found for the given date.")
    })
    @GetMapping("/{date}/highest-normalized")
    public ResponseEntity<NormalizedData> getHighestNormalizedDataForDate(
            @ApiParam(value = "Date should be in 'yyyy-MM-dd' format", required = true, example = "2022-01-01") @PathVariable String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format.");
        }
        return Optional.ofNullable(cryptoDataService.getHighestNormalizedRangeForSpecificDay(localDate))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
