package com.xm.cryptoservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xm.cryptoservice.config.Constants;

/**
 * NormalizedData class represents the normalized data of a cryptocurrency.
 * It includes properties like symbol and normalizedRange.
 */
@Entity
@Table(name = "normalized_data")
public class NormalizedData {

    @Id
    private String symbol;
    @Column(columnDefinition = "DECIMAL(" + Constants.DECIMAL_PRECISION + ", " + Constants.DECIMAL_SCALE + ")")
    private BigDecimal normalizedRange;

    /**
     * Default constructor for the NormalizedData class.
     */
    public NormalizedData() {
    }

    /**
     * Constructor for the NormalizedData class.
     * 
     * @param symbol          the symbol of the crypto
     * @param normalizedRange the normalized range of the crypto
     */
    public NormalizedData(String symbol, BigDecimal normalizedRange) {
        this.symbol = symbol;
        this.normalizedRange = normalizedRange;
    }

    /**
     * This method returns the symbol of the crypto.
     * 
     * @return the symbol of the crypto
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * This method sets the symbol of the crypto.
     * 
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * This method returns the normalized range of the crypto.
     * 
     * @return the normalized range of the crypto
     */
    public BigDecimal getNormalizedRange() {
        return normalizedRange;
    }

    /**
     * This method sets the normalized range of the crypto.
     * 
     * @param normalizedRange the normalized range to set
     */
    public void setNormalizedRange(BigDecimal normalizedRange) {
        this.normalizedRange = normalizedRange;
    }
}
