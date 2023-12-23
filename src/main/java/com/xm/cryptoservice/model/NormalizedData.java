package com.xm.cryptoservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xm.cryptoservice.config.Constants;

@Entity
@Table(name = "normalized_data")
public class NormalizedData {

    @Id
    private String symbol;
    @Column(columnDefinition = "DECIMAL(" + Constants.DECIMAL_PRECISION + ", " + Constants.DECIMAL_SCALE + ")")
    private BigDecimal normalizedRange;

    public NormalizedData() {
    }

    public NormalizedData(String symbol, BigDecimal normalizedRange) {
        this.symbol = symbol;
        this.normalizedRange = normalizedRange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getNormalizedRange() {
        return normalizedRange;
    }

    public void setNormalizedRange(BigDecimal normalizedRange) {
        this.normalizedRange = normalizedRange;
    }
}
