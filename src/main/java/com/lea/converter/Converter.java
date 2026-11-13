package com.lea.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

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
}