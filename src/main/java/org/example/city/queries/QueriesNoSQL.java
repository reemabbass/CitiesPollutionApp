package org.example.city.queries;

import org.example.city.other.CityRecord;

import java.util.ArrayList;

public class QueriesNoSQL {

    // מדינה עם זיהום אוויר הכי גבוה – ללא SQL
    public static void highestAirNoSQL(ArrayList<CityRecord> list) {

        double max = -1;
        String country = "";

        for (CityRecord c : list) {
            if (c.getAirQuality() > max) {
                max = c.getAirQuality();
                country = c.getCountry();
            }
        }

        System.out.println("ללא SQL – המדינה עם הזיהום הכי גבוה באוויר:");
        System.out.println(country + " | " + max);
    }


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
    public static void citiesWaterPollutionRangeNoSQL(ArrayList<CityRecord> list, double x, double y) {
        int count = 0;
        System.out.println("ערים עם זיהום אוויר בין " + x + " ל-" + y + ":");

        for (CityRecord c : list) {
            if (c.getWaterPollution() >= x && c.getWaterPollution() <= y) {
                count++;
                System.out.println(c.getCity() + " | " +
                        c.getCountry() + " | " +
                        c.getWaterPollution());
            }
        }

        System.out.println("מספר הערים עם זיהום מים הוא: " + count);
    }

}
