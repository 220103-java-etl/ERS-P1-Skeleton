package com.revature.repositories;
import java.util.List;

public interface GenericDAO<T> {

    // Create
    T add(T t);

    // Read
    T getById(Integer id);

    List<T> getAll();

    // Update
    void update(T t);

    // Delete
    void delete(Integer id);

}
