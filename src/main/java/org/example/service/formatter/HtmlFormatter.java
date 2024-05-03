package org.example.service.formatter;

import org.example.model.export.ExportTableData;

public class HtmlFormatter implements Formatter {

    /**
     * Creates simple html string from given data
     *
     * @param exportTableData input table data @{@link ExportTableData}
     */
    @Override
    public String format(ExportTableData<?> exportTableData) {
        StringBuilder sb = new StringBuilder();

        sb.append("<table>");
        sb.append("<tr bgcolor=\"gray\">");
        exportTableData.getHeaderRow().forEach(column -> sb.append("<th>").append(column).append("</th>"));
        sb.append("</tr>");
        exportTableData.getBodyRows().forEach(columns -> {
            sb.append("<tr>");
            columns.forEach(column -> sb.append("<td>").append(column).append("</td>"));
            sb.append("</tr>");
        });
        sb.append("<tr bgcolor=\"yellow\">");
        exportTableData.getFooterRow().forEach(column -> sb.append("<td>").append(column).append("</td>"));
        sb.append("</tr>");

        return sb.toString();
    }
}
