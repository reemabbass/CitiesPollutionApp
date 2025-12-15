package org.example.city.app;

import org.example.city.db.JDBC;
import org.example.city.other.CSVLoader;
import org.example.city.other.CityRecord;
import org.example.city.queries.QueriesNoSQL;
import org.example.city.queries.QueriesSQL;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

    /*
        @Author Reem Abbas - 322732470
        @Author Atheer Assad - 326448255
     */

public class App {

    public static void main(String[] args) {

        Connection conn = JDBC.connect();
        if (conn != null) {
            System.out.println("Connected to MySQL!");
        } else {
            System.out.println("Connection failed.");
        }
        Scanner sc = new Scanner(System.in);

        // טעינת נתוני CSV למערכת
        ArrayList<CityRecord> data =
                CSVLoader.load("/citiespollutiondb.csv");


        while (true) {
            System.out.println("\n=== מערכת זיהום ערים ===");
            System.out.println("1) המדינה עם זיהום האוויר הגבוה ביותר (SQL)");
            System.out.println("2) ספירת ערים בטווח זיהום מים (SQL)");
            System.out.println("3) המדינה עם זיהום מים הגבוה ביותר (ללא SQL)");
            System.out.println("4) ספירת ערים בטווח זיהום מים (ללא SQL)");
            System.out.println("5) המדינה עם זיהום האוויר הגבוה ביותר (ללא SQL)");
            System.out.println("6) המדינה עם זיהום המים הגבוה ביותר (SQL)");
            System.out.println("0) יציאה");
            System.out.print("בחר אפשרות: ");


            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    QueriesSQL.highestAirPollution();
                    break;

                case 2:
                    System.out.print("X = ");
                    double x = sc.nextDouble();
                    System.out.print("Y = ");
                    double y = sc.nextDouble();
                    QueriesSQL.citiesWaterRange(x, y);
                    break;

                case 3:
                    QueriesNoSQL.highestWaterNoSQL(data);
                    break;

                case 4:
                    System.out.print("X = ");
                    x = sc.nextDouble();
                    System.out.print("Y = ");
                    y = sc.nextDouble();
                    QueriesNoSQL.citiesWaterPollutionRangeNoSQL(data, x, y);
                    break;

                case 5:
                    QueriesNoSQL.highestAirNoSQL(data);
                    break;

                case 6:
                    if (conn != null) {
                        QueriesSQL.highestWaterSQL(conn);
                    } else {
                        System.out.println("אין חיבור לבסיס נתונים (SQL).");
                    }
                    break;

                case 0:
                    System.out.println("להתראות!");
                    return;
            }
        }
    }
}