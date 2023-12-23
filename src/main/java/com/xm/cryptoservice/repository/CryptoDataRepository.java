package com.xm.cryptoservice.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xm.cryptoservice.model.CryptoData;

/**
 * CryptoDataRepository interface for performing operations on the CryptoData
 * entity.
 */
@Repository
public interface CryptoDataRepository extends JpaRepository<CryptoData, Long> {

    /**
     * Finds the minimum price for a given symbol.
     * 
     * @param symbol the symbol to search for
     * @return the minimum price
     */
    @Query("SELECT MIN(c.price) FROM CryptoData c WHERE c.symbol = ?1")
    BigDecimal findMinPriceBySymbol(String symbol);

    /**
     * Finds the maximum price for a given symbol.
     * 
     * @param symbol the symbol to search for
     * @return the maximum price
     */
    @Query("SELECT MAX(c.price) FROM CryptoData c WHERE c.symbol = ?1")
    BigDecimal findMaxPriceBySymbol(String symbol);

    /**
     * Finds the latest data for a given symbol.
     * 
     * @param symbol the symbol to search for
     * @return the latest data
     */
    @Query(value = "SELECT * FROM crypto_data c WHERE c.symbol = ?1 ORDER BY c.timestamp DESC LIMIT 1", nativeQuery = true)
    Optional<CryptoData> findLatestDataBySymbol(String symbol);

    /**
     * Finds the oldest data for a given symbol.
     * 
     * @param symbol the symbol to search for
     * @return the oldest data
     */
    @Query(value = "SELECT * FROM crypto_data c WHERE c.symbol = ?1 ORDER BY c.timestamp ASC LIMIT 1", nativeQuery = true)
    Optional<CryptoData> findOldestDataBySymbol(String symbol);

    /**
     * Finds all data for a given symbol.
     * 
     * @param symbol the symbol to search for
     * @return a list of all data
     */
    @Query("SELECT c FROM CryptoData c WHERE c.symbol = ?1")
    List<CryptoData> findDataBySymbol(String symbol);

    /**
     * Finds all distinct symbols.
     * 
     * @return a list of all symbols
     */
    @Query("SELECT DISTINCT c.symbol FROM CryptoData c")
    List<String> findAllSymbols();

    /**
     * Finds all data within a given timestamp range.
     * 
     * @param fromTimestamp the start of the range
     * @param toTimestamp   the end of the range
     * @return a list of all data within the range
     */
    @Query("SELECT c FROM CryptoData c WHERE c.timestamp BETWEEN :fromTimestamp AND :toTimestamp")
    List<CryptoData> findAllWithinTimestamp(@Param("fromTimestamp") Long fromTimestamp,
            @Param("toTimestamp") Long toTimestamp);

}
