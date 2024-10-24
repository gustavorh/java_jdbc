package dev.gustavorh.java_jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static String url = "jdbc:mysql://localhost:3306/java_curso";
    private static String username = "root";
    private static String password = "sasa";
    private static Connection conn;

    public static Connection getInstance() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, password);
        }
        return conn;
    }
}
