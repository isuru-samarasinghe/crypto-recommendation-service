package com.xm.cryptoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xm.cryptoservice.model.CryptoDataSummary;

@Repository
public interface CryptoDataSummaryRepository extends JpaRepository<CryptoDataSummary, String> {
}