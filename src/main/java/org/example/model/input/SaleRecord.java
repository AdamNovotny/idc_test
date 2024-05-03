package org.example.model.input;

import java.math.BigDecimal;

/**
 * Object that represent one row of input scv file
 */
public class SaleRecord {
    private String country;
    private String timescale;
    private String vendor;
    private BigDecimal units;

    public SaleRecord() {
    }

    public SaleRecord(String country, String timescale, String vendor, BigDecimal units) {
        this.country = country;
        this.timescale = timescale;
        this.vendor = vendor;
        this.units = units;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimescale() {
        return timescale;
    }

    public void setTimescale(String timescale) {
        this.timescale = timescale;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public BigDecimal getUnits() {
        return units;
    }

    public void setUnits(BigDecimal units) {
        this.units = units;
    }
}
