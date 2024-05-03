package org.example.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Placeholder class that represents market share data for given country and given quarter
 * TODO quarter is represented by string which is far from ideal (simplified reason..) -> convert to date units will make more sense,
 */
public class QuarterlyMarketShare {

    private static final DecimalFormat df = new DecimalFormat("0.0");

    private final String country;
    private final String quarter;
    private BigDecimal totalUnits = BigDecimal.ZERO;
    private LinkedHashMap<String, BigDecimal> marketShares = new LinkedHashMap<>();

    public QuarterlyMarketShare(String country, String quarter) {
        this.country = country;
        this.quarter = quarter;
    }

    public String getCountry() {
        return country;
    }

    public String getQuarter() {
        return quarter;
    }

    public BigDecimal getTotalUnits() {
        return totalUnits;
    }

    public Map<String, BigDecimal> getMarketShares() {
        return marketShares;
    }

    public void calculateTotalUnits() {
        totalUnits = marketShares.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getUnitsByVendor(String vendor) {
        return marketShares.get(vendor);
    }

    /**
     * @param vendor
     * @return percentage of share for given vendor (formated to string representation given by hardcoded decimal format)
     */
    public String getShareByVendor(String vendor) {
        if (totalUnits.equals(BigDecimal.ZERO)) {
            return df.format(0);
        }

        if (marketShares.get(vendor) == null) {
            return df.format(0);
        }

        return df.format(marketShares.get(vendor)
            .divide(totalUnits, MathContext.DECIMAL32)
            .multiply(BigDecimal.valueOf(100))
        );
    }

    public void sortMarketSharesByVendor(Order order) {
        marketShares = marketShares.entrySet()
            .stream()
            .sorted(Order.DESC.equals(order) ?
                    Map.Entry.comparingByKey(String.CASE_INSENSITIVE_ORDER.reversed()) :
                    Map.Entry.comparingByKey(String.CASE_INSENSITIVE_ORDER))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public void sortMarketSharesByUnits(Order order) {
        marketShares = marketShares.entrySet()
            .stream()
            .sorted(Order.DESC.equals(order) ? Map.Entry.comparingByValue(Comparator.reverseOrder()) : Map.Entry.comparingByValue())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    public int getRowByVendor(String vendor) {
        if (!marketShares.containsKey(vendor)) {
            return -1;
        }

        return new ArrayList<>(marketShares.keySet()).indexOf(vendor) + 1;
    }

    public String getVendorByIndex(int index) {
        return new ArrayList<>(marketShares.keySet()).get(index);
    }
}
