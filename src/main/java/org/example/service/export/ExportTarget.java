package org.example.service.export;

/**
 * Helper class that holds information with export parameters
 * TODO to be extended by for example folder, different targets etc...
 */
public class ExportTarget {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
