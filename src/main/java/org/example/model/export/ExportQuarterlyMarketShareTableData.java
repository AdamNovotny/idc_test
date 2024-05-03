package org.example.model.export;

import org.example.model.QuarterlyMarketShare;

import java.util.List;

public class ExportQuarterlyMarketShareTableData extends ExportTableData<QuarterlyMarketShare> {

    public ExportQuarterlyMarketShareTableData(QuarterlyMarketShare data) {
        super(data);
    }

    @Override
    protected void prepareHeaderData() {
        headerRow.add("Vendor");
        headerRow.add("Units");
        headerRow.add("Share");
    }

    @Override
    protected void prepareBodyData() {
        data.getMarketShares().forEach((key, value) -> bodyRows.add(List.of(key, value.toString(), data.getShareByVendor(key) + "%")));
    }

    @Override
    protected void prepareFooterData() {
        footerRow.add("Total");
        footerRow.add(data.getTotalUnits().toString());
        footerRow.add("100%");
    }
}
