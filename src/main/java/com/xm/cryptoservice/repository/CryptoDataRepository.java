package com.xm.cryptoservice.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xm.cryptoservice.model.CryptoData;

@Repository
public interface CryptoDataRepository extends JpaRepository<CryptoData, Long> {

    @Query("SELECT MIN(c.price) FROM CryptoData c WHERE c.symbol = ?1")
    BigDecimal findMinPriceBySymbol(String symbol);

    @Query("SELECT MAX(c.price) FROM CryptoData c WHERE c.symbol = ?1")
    BigDecimal findMaxPriceBySymbol(String symbol);

    @Query(value = "SELECT * FROM crypto_data c WHERE c.symbol = ?1 ORDER BY c.timestamp DESC LIMIT 1", nativeQuery = true)
    Optional<CryptoData> findLatestDataBySymbol(String symbol);

    @Query(value = "SELECT * FROM crypto_data c WHERE c.symbol = ?1 ORDER BY c.timestamp ASC LIMIT 1", nativeQuery = true)
    Optional<CryptoData> findOldestDataBySymbol(String symbol);

    @Query("SELECT c FROM CryptoData c WHERE c.symbol = ?1")
    List<CryptoData> findDataBySymbol(String symbol);

    @Query("SELECT DISTINCT c.symbol FROM CryptoData c")
    List<String> findAllSymbols();

    @Query("SELECT c FROM CryptoData c WHERE c.timestamp BETWEEN :fromTimestamp AND :toTimestamp")
    List<CryptoData> findAllWithinTimestamp(@Param("fromTimestamp") Long fromTimestamp,
            @Param("toTimestamp") Long toTimestamp);

}
