package org.example.model.export;

import java.util.ArrayList;
import java.util.List;

/**
 * Holder of table specific data, each implementation must define own implementation of how to create header, body and footer row
 * TODO: ensure all rows have same number of columns (implement checks/exceptions...)
 *
 * @param <T>
 */
public abstract class ExportTableData<T> {

    protected final T data;

    protected List<String> headerRow = new ArrayList<>();

    protected List<List<String>> bodyRows = new ArrayList<>();

    protected List<String> footerRow = new ArrayList<>();

    public ExportTableData(T data) {
        this.data = data;
        prepareHeaderData();
        prepareBodyData();
        prepareFooterData();
    }

    protected abstract void prepareHeaderData();

    protected abstract void prepareBodyData();

    protected abstract void prepareFooterData();

    public List<String> getHeaderRow() {
        return headerRow;
    }

    public List<List<String>> getBodyRows() {
        return bodyRows;
    }

    public List<String> getFooterRow() {
        return footerRow;
    }
}
