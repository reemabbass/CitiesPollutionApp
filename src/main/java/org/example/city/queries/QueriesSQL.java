package org.example.city.queries;

import java.sql.*;
import org.example.city.db.JDBC;

public class QueriesSQL {

    // מדינות עם זיהום אוויר הכי גבוה
    public static void highestAirPollution() {

        String sql =
                "SELECT Country, AirQuality " +
                        "FROM CitiesPollution " +
                        "ORDER BY AirQuality DESC " +
                        "LIMIT 1";

        try (Connection conn = JDBC.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("המדינה עם זיהום האוויר הגבוה ביותר (SQL):");
                System.out.println(
                        rs.getString("Country") +
                                " | " +
                                rs.getDouble("AirQuality")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // מדינה עם זיהום מים הכי גבוה – עם SQL
    public static void highestWaterSQL(Connection conn) {

        String sql =
                "SELECT Country, WaterPollution " +
                        "FROM citiespollution " +
                        "ORDER BY WaterPollution DESC " +
                        "LIMIT 1";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String country = rs.getString("Country");
                double max = rs.getDouble("WaterPollution");

                System.out.println("עם SQL – המדינה עם הזיהום הכי גבוה במים:");
                System.out.println(country + " | " + max);
            }

        } catch (SQLException e) {
            System.out.println("שגיאה בשאילתת SQL (highestWaterSQL)");
            e.printStackTrace();
        }
    }

    // ערים עם זיהום מים בין X ל-Y
    public static void citiesWaterRange(double x, double y) {
        String sql = "SELECT city, country, WaterPollution " +
                "FROM CitiesPollution WHERE WaterPollution BETWEEN ? AND ?";
        int count = 0;
        try (Connection conn = JDBC.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, x);
            ps.setDouble(2, y);

            ResultSet rs = ps.executeQuery();

            System.out.println("ערים עם זיהום מים בין " + x + " ל-" + y + ":");
            while (rs.next()) {
                count ++;
                System.out.println(rs.getString("city") + " | " +
                        rs.getString("country") + " | " +
                        rs.getDouble("waterpollution"));
            }

            System.out.println("מספר הערים עם זיהום מים הוא: " + count);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
