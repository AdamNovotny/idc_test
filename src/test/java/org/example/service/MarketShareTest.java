package org.example.service;

import org.example.Main;
import org.example.model.Order;
import org.example.model.QuarterlyMarketShare;
import org.example.model.export.ExportQuarterlyMarketShareTableData;
import org.example.model.input.SaleRecord;
import org.example.service.export.ExportTarget;
import org.example.service.export.Exporter;
import org.example.service.export.FileExporter;
import org.example.service.formatter.Formatter;
import org.example.service.formatter.HtmlFormatter;
import org.example.util.CsvParser;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MarketShareTest {

    @Test
    public void testMarketShare() {
        MarketShareService marketShareService = new MarketShareService();
        Formatter formatter = new HtmlFormatter();
        Exporter exporter = new FileExporter();

        String path = Main.class.getClassLoader().getResource("data.csv").getPath();

        List<SaleRecord> records = CsvParser.parseContent(path, SaleRecord.class);

        QuarterlyMarketShare marketShare = marketShareService.getQuarterlyMarketShare("Slovakia", "2010 Q3", records);

        assertEquals(7, marketShare.getMarketShares().size());
        assertEquals(7, marketShare.getRowByVendor("ASUS"));
        assertEquals(new BigDecimal("2137.856193"), marketShare.getMarketShares().get("Dell"));
        assertEquals("28,1", marketShare.getShareByVendor("Dell"));

        marketShare.sortMarketSharesByUnits(Order.ASC);
        assertEquals(1, marketShare.getRowByVendor("ASUS"));

        marketShare.sortMarketSharesByUnits(Order.DESC);
        assertEquals(7, marketShare.getRowByVendor("ASUS"));

        marketShare.sortMarketSharesByVendor(Order.ASC);
        assertEquals(3, marketShare.getRowByVendor("ASUS"));

        ExportTarget exportTarget = new ExportTarget();
        exportTarget.setFileName("table.html");
        exporter.export(formatter.format(new ExportQuarterlyMarketShareTableData(marketShare)), exportTarget);

        File f = new File("table.html");
        assertTrue(f.exists());
    }

}
