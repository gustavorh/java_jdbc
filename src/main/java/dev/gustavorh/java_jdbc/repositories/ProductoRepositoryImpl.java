package dev.gustavorh.java_jdbc.repositories;

import dev.gustavorh.java_jdbc.models.Producto;
import dev.gustavorh.java_jdbc.utils.DbConnection;

import java.sql.*;
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {

            while (rs.next()) {
                Producto p = getProducto(rs);

                productos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productos;
    }


    @Override
    public Producto getById(Long id) {
        Producto producto = null;

        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM productos WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return producto;
    }

    @Override
    public void add(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre = ?, precio=? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos (nombre, precio, fecha_registro) VALUES (?, ?, ?)";
        }

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(3, producto.getId());
            } else {
                stmt.setDate(3, new Date(producto.getFecha_registro().getTime()));
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id=?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFecha_registro(rs.getDate("fecha_registro"));
        return p;
    }
}
