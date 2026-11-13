package com.lea.converter;

public class Main {

    public static void main(String[] args) {

        if (args.length != 3) {

            System.out.println(
                    "Usage:\n" +
                    "json2csv input.json output.csv");

            return;
        }

        try {

            if (args[0].equalsIgnoreCase("json2csv")) {

                Converter.jsonToCsv(args[1], args[2]);

                System.out.println("Conversion successful.");

            } else {

                System.out.println("Unknown command.");
            }

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}