package org.example.city.queries;

import java.sql.*;
import org.example.city.db.JDBC;

public class QueriesSQL {

    // מדינות עם זיהום אוויר הכי גבוה
    public static void highestAirPollution() {
        String sql = "SELECT Country, AVG(AirQuality) AS avgAir " +
                "FROM CitiesPollution GROUP BY country " +
                "ORDER BY avgAir DESC LIMIT 1";

        try (Connection conn = JDBC.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("המדינה עם זיהום האוויר הגבוה ביותר:");
                System.out.println(rs.getString("country") +
                        " | " + rs.getDouble("avgAir"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ערים עם זיהום מים בין X ל-Y
    public static void citiesWaterRange(double x, double y) {
        String sql = "SELECT city, country, WaterPollution " +
                "FROM CitiesPollution WHERE WaterPollution BETWEEN ? AND ?";

        try (Connection conn = JDBC.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, x);
            ps.setDouble(2, y);

            ResultSet rs = ps.executeQuery();

            System.out.println("ערים עם זיהום מים בין " + x + " ל-" + y + ":");
            while (rs.next()) {
                System.out.println(rs.getString("city") + " | " +
                        rs.getString("country") + " | " +
                        rs.getDouble("water_pollution"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
