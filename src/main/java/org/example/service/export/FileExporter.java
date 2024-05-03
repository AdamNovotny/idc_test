package org.example.service.export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileExporter implements Exporter {

    /**
     * Simple file exporter that create new file with given content
     *
     * @param content content of the file to be created
     * @param target  @{@link ExportTarget} definition
     */
    @Override
    public void export(String content, ExportTarget target) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target.getFileName()))) {
            writer.append(content);
        } catch (IOException e) {
            throw new RuntimeException("could not process file...", e);
        }
    }
}
