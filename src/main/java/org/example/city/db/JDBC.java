package org.example.city.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/CitiesPollutionDB";
    private static final String USER = "root";   // שנה לפי הצורך
    private static final String PASS = "2800";       // שנה לפי הצורך

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }
}
