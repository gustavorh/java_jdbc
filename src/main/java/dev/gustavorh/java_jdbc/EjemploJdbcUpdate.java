package dev.gustavorh.java_jdbc;

import dev.gustavorh.java_jdbc.models.Producto;
import dev.gustavorh.java_jdbc.repositories.ProductoRepositoryImpl;
import dev.gustavorh.java_jdbc.repositories.Repository;
import dev.gustavorh.java_jdbc.utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcUpdate {
    public static void main(String[] args) {
        try (Connection conn = DbConnection.getInstance()) {
            Repository<Producto> repository = new ProductoRepositoryImpl();

            System.out.println("=================== Get All ===================");
            repository.getAll().forEach(System.out::println);

            System.out.println("=================== Get By Id ===================");
            System.out.println(repository.getById(1L));

            System.out.println("=================== Edit ===================");
            Producto p = new Producto();
            p.setId(3L);
            p.setNombre("Teclado Razer");
            p.setPrecio(700);

            repository.add(p);
            System.out.println("Producto actualizado!");

            repository.getAll().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
