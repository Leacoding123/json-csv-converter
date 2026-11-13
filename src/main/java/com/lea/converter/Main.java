package com.lea.converter;

public class Main {

    public static void main(String[] args) {

        if (args.length != 3) {

            System.out.println(
                    "Usage:\n" +
                    "json2csv input.json output.csv\n" +
                    "csv2json input.csv output.json");

            return;
        }

        try {

            if (args[0].equalsIgnoreCase("json2csv")) {

                Converter.jsonToCsv(args[1], args[2]);

                System.out.println("JSON to CSV conversion successful.");

            } else if (args[0].equalsIgnoreCase("csv2json")) {

                Converter.csvToJson(args[1], args[2]);

                System.out.println("CSV to JSON conversion successful.");

            } else {

                System.out.println("Unknown command.");
            }
            } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}