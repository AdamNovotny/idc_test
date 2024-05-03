package org.example.service.formatter;

import org.example.model.export.ExportTableData;

public interface Formatter {

    /**
     * Function that format input @{@link ExportTableData} to desired string format
     *
     * @param exportTableData input table data @{@link ExportTableData}
     */
    public String format(ExportTableData<?> exportTableData);
}
