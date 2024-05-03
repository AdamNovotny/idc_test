package org.example.service;

import org.example.model.QuarterlyMarketShare;
import org.example.model.input.SaleRecord;

import java.math.BigDecimal;
import java.util.List;

public class MarketShareService {

    /**
     * Filters input parsed scv data based on given country and timescale and converts it to @{@link QuarterlyMarketShare}
     *
     * @param country
     * @param quarter string representation of year and quarter as givne in source date (eg. 2010 Q4)
     * @param data    all rows parsed from csv file
     * @return QuarterlyMarketShare
     */
    public QuarterlyMarketShare getQuarterlyMarketShare(String country, String quarter, List<SaleRecord> data) {
        QuarterlyMarketShare quarterlyMarketShare = new QuarterlyMarketShare(country, quarter);

        data.stream()
            .filter(saleRecord -> saleRecord.getCountry().equals(country) && saleRecord.getTimescale().equals(quarter))
            .forEach(
                saleRecord -> quarterlyMarketShare.getMarketShares().merge(saleRecord.getVendor(), saleRecord.getUnits(), BigDecimal::add));

        quarterlyMarketShare.calculateTotalUnits();

        return quarterlyMarketShare;
    }

}
