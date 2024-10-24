package dev.gustavorh.java_jdbc.repositories;

import dev.gustavorh.java_jdbc.models.Producto;
import dev.gustavorh.java_jdbc.utils.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements Repository<Producto> {
    private Connection getConnection() throws SQLException {
        return DbConnection.getInstance();
    }

    @Override
    public List<Producto> getAll() {
        List<Producto> productos = new ArrayList<Producto>();

        try (Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select * from producto")) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getLong("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getInt("precio"));
                p.setFecha_registro(rs.getDate("fecha_registro"));

                productos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productos;
    }

    @Override
    public Producto getById(Long id) {
        return null;
    }

    @Override
    public void add(Producto producto) {

    }

    @Override
    public void delete(Long id) {

    }
}
