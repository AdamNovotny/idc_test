package org.example.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public static final char SEPARATOR = ',';

    /**
     * CSV parser method which parses input csv file to list of POJO objects
     *
     * @param file        path to csv file
     * @param targetClass class we want to file to be parsed
     * @param <T>
     * @return list of objects T
     */
    public static <T> List<T> parseContent(String file, Class<? extends T> targetClass) {
        HeaderColumnNameMappingStrategy<T> headerColumnNameMappingStrategy = new HeaderColumnNameMappingStrategy<>();
        headerColumnNameMappingStrategy.setType(targetClass);

        List<T> parsedData = new ArrayList<>();
        try {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder(new FileReader(file)).withSeparator(SEPARATOR)
                .withIgnoreLeadingWhiteSpace(true)
                .withType(targetClass)
                .withMappingStrategy(headerColumnNameMappingStrategy)
                .withIgnoreEmptyLine(true)
                .build();

            parsedData = csvToBean.parse();
        } catch (FileNotFoundException e) {
            //implementation depend on business definition, for now no file - no data
        }

        return parsedData;
    }

}
