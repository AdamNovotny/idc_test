package org.example.service.formatter;

import org.example.model.export.ExportTableData;

public class ExcelFormatter implements Formatter {
    @Override
    public String format(ExportTableData<?> exportTableData) {
        // not implemented
        // function will use exportTableData data similarly as HtmlFormatter to iterate over rows and create xls formatted string
        return "";
    }
}
