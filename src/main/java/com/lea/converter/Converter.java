package com.lea.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Converter {

    private static final Logger LOGGER =
        Logger.getLogger(Converter.class.getName());

    public static void jsonToCsv(String jsonFile, String csvFile) throws Exception {

        try {

            DataValidator.validateFile(jsonFile);
            ObjectMapper mapper = new ObjectMapper();

            LOGGER.fine("Starting JSON to CSV conversion: " + jsonFile);

            List<Map<String, Object>> data =
                    mapper.readValue(
                            new File(jsonFile),
                            new TypeReference<List<Map<String, Object>>>() {}
                    );
                    
            LOGGER.info("Loaded " + data.size() + " records from JSON file");

            if (data.isEmpty()) {
                LOGGER.warning("JSON file is empty");
                return;
            }

            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {

                String[] headers =
                        data.get(0).keySet().toArray(new String[0]);

                writer.writeNext(headers);

                for (Map<String, Object> row : data) {

                    String[] values = Arrays.stream(headers)
                            .map(header -> String.valueOf(row.get(header)))
                            .toArray(String[]::new);

                    writer.writeNext(values);
                }
            }
            LOGGER.info("Successfully converted JSON to CSV: " + csvFile);

        } catch (Exception e) {
            LOGGER.log(
                Level.SEVERE,
                "JSON to CSV conversion failed",
                e
            );

            throw e; 
        }
    } 

    public static void csvToJson(String csvFile, String jsonFile) throws Exception {

        try { 
            DataValidator.validateFile(csvFile);
            LOGGER.fine("Starting CSV to JSON conversion: " + csvFile);

            List<Map<String, String>> jsonList = new ArrayList<>();

            try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {

                List<String[]> rows = reader.readAll();
                LOGGER.info("Read " + rows.size() + " rows from CSV file");
                DataValidator.validateCsvRows(rows);

                if (rows.isEmpty()) {
                    LOGGER.warning("CSV file is empty");
                    return;
                }

                String[] headers = rows.get(0);

                for (int i = 1; i < rows.size(); i++) {

                    Map<String, String> record = new LinkedHashMap<>();

                    for (int j = 0; j < headers.length; j++) {
                        record.put(headers[j], rows.get(i)[j]);
                    }

                    jsonList.add(record);
                }
            }

            ObjectMapper mapper = new ObjectMapper();

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(jsonFile), jsonList);
            LOGGER.info("Successfully converted CSV to JSON: " + jsonFile);

        } catch (Exception e) {

            LOGGER.log(
                    Level.SEVERE,
                    "CSV to JSON conversion failed",
                    e
            );

            throw e;
        }
    }
}