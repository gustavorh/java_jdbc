package dev.gustavorh.java_jdbc;

import dev.gustavorh.java_jdbc.utils.DbConnection;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {
        try (
                Connection conn = DbConnection.getInstance();
                Statement stmt = conn.createStatement();
                ResultSet resultado = stmt.executeQuery("select * from productos")
        ) {

            while (resultado.next()) {
                System.out.print(resultado.getInt("id"));
                System.out.print(" | ");
                System.out.print(resultado.getString("nombre"));
                System.out.print(" | ");
                System.out.print(resultado.getInt("precio"));
                System.out.print(" | ");
                System.out.println(resultado.getDate("fecha_registro"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
