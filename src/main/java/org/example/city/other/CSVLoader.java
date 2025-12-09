package org.example.city.other;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CSVLoader {

    // split on commas that are NOT inside quotes (safe even if you still have some quotes)
    private static final String CSV_SPLIT_REGEX = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    public static ArrayList<CityRecord> load(String path) {
        ArrayList<CityRecord> list = new ArrayList<>();

        try (
                InputStream is = CSVLoader.class.getResourceAsStream(path);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, StandardCharsets.UTF_8))
        ) {
            if (is == null) {
                throw new IllegalStateException("File not found on classpath: " + path);
            }

            String line;

            // 1) read and ignore header
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] row = line.split(CSV_SPLIT_REGEX);

                if (row.length < 5) {
                    // bad row, skip it
                    System.out.println("Skipping bad row: " + line);
                    continue;
                }

                // clean text columns
                String city    = row[0].trim().replace("\"", "");
                String region  = row[1].trim().replace("\"", "");
                String country = row[2].trim().replace("\"", "");

                // always take the last two columns as numbers
                String airStr   = row[row.length - 2].trim();
                String waterStr = row[row.length - 1].trim();

                try {
                    double air   = Double.parseDouble(airStr);
                    double water = Double.parseDouble(waterStr);

                    list.add(new CityRecord(city, region, country, air, water));

                } catch (NumberFormatException nfe) {
                    System.out.println("Skipping row with bad numbers: " + line);
                    nfe.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }
}
