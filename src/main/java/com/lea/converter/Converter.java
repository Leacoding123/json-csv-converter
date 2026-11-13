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

public class Converter {

    public static void jsonToCsv(String jsonFile, String csvFile) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        List<Map<String, Object>> data =
                mapper.readValue(
                        new File(jsonFile),
                        new TypeReference<List<Map<String, Object>>>() {}
                );

        if (data.isEmpty()) {
            System.out.println("JSON file is empty.");
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
    } 

    public static void csvToJson(String csvFile, String jsonFile) throws Exception {

    List<Map<String, String>> jsonList = new ArrayList<>();

    try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {

        List<String[]> rows = reader.readAll();

        if (rows.isEmpty()) {
            System.out.println("CSV file is empty.");
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
}
}