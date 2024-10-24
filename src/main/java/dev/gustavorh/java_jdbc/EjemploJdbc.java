package dev.gustavorh.java_jdbc;

import dev.gustavorh.java_jdbc.models.Producto;
import dev.gustavorh.java_jdbc.repositories.ProductoRepositoryImpl;
import dev.gustavorh.java_jdbc.repositories.Repository;
import dev.gustavorh.java_jdbc.utils.DbConnection;

import java.sql.*;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) {
        try (Connection conn = DbConnection.getInstance()) {
            Repository<Producto> repository = new ProductoRepositoryImpl();

            System.out.println("=================== Get All ===================");
            repository.getAll().forEach(System.out::println);

            System.out.println("=================== Get By Id ===================");
            System.out.println(repository.getById(1L));

            System.out.println("=================== Create ===================");
            Producto p = new Producto();
            p.setNombre("Teclado Mecanico");
            p.setPrecio(500);
            p.setFecha_registro(new Date());

            repository.add(p);
            System.out.println("Producto guardado!");

            repository.getAll().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
