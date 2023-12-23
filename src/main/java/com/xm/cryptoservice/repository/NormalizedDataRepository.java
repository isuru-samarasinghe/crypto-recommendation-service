package com.xm.cryptoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xm.cryptoservice.model.NormalizedData;

@Repository
public interface NormalizedDataRepository extends JpaRepository<NormalizedData, String> {
    @Query("SELECT n FROM NormalizedData n ORDER BY n.normalizedRange DESC")
    List<NormalizedData> findAllOrderByNormalizedRangeDesc();
}