package org.example.city.queries;

import org.example.city.other.CityRecord;

import java.util.ArrayList;

public class QueriesNoSQL {

    // מדינה עם זיהום מים הכי גבוה – ללא SQL
    public static void highestWaterNoSQL(ArrayList<CityRecord> list) {

        double max = -1;
        String country = "";

        for (CityRecord c : list) {
            if (c.getWaterPollution() > max) {
                max = c.getWaterPollution();
                country = c.getCountry();
            }
        }

        System.out.println("ללא SQL – המדינה עם הזיהום הכי גבוה במים:");
        System.out.println(country + " | " + max);
    }

    // מספר ערים בטווח זיהום אוויר X-Y – ללא SQL
    public static void countAirRange(ArrayList<CityRecord> list, double x, double y) {

        int count = 0;

        for (CityRecord c : list) {
            if (c.getAirQuality() >= x && c.getAirQuality() <= y) {
                count++;
            }
        }

        System.out.println("מספר הערים בטווח האוויר " + x + "-" + y + ": " + count);
    }

}
