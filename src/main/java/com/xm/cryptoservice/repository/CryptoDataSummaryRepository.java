package com.xm.cryptoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xm.cryptoservice.model.CryptoDataSummary;

/**
 * CryptoDataSummaryRepository interface for performing operations on the CryptoDataSummary entity.
 * It extends JpaRepository to provide methods to perform CRUD operations.
 */
@Repository
public interface CryptoDataSummaryRepository extends JpaRepository<CryptoDataSummary, String> {
}