package com.xm.cryptoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xm.cryptoservice.model.NormalizedData;

/**
 * NormalizedDataRepository interface for performing operations on the
 * NormalizedData entity.
 * It extends JpaRepository to provide methods to perform CRUD operations.
 */
@Repository
public interface NormalizedDataRepository extends JpaRepository<NormalizedData, String> {

    /**
     * Finds all NormalizedData entities and orders them by normalizedRange in
     * descending order.
     * 
     * @return a list of all NormalizedData entities ordered by normalizedRange in
     *         descending order
     */
    @Query("SELECT n FROM NormalizedData n ORDER BY n.normalizedRange DESC")
    List<NormalizedData> findAllOrderByNormalizedRangeDesc();
}