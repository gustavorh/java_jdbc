package dev.gustavorh.java_jdbc.repositories;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();
    T getById(Long id);
    void add(T t);
    void delete(Long id);
}
