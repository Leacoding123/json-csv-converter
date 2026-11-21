package com.lea.converter;

import java.io.File;
import java.util.List;

public class DataValidator {

    public static void validateFile(String filePath)
            throws ConversionException {

        File file = new File(filePath);

        if (!file.exists()) {
            throw new ConversionException(
                    "File does not exist: " + filePath);
        }

        if (file.length() == 0) {
            throw new ConversionException(
                    "File is empty: " + filePath);
        }
    }

    public static void validateCsvRows(List<String[]> rows)
            throws ConversionException {

        if (rows.isEmpty()) {
            throw new ConversionException(
                    "CSV file contains no data");
        }

        int columnCount = rows.get(0).length;

        for (int i = 1; i < rows.size(); i++) {

            if (rows.get(i).length != columnCount) {
                throw new ConversionException(
                        "Invalid CSV format at row " + (i + 1)
                );
            }
        }
    }
}