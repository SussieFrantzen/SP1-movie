package app.dao;

import java.util.List;

public interface DAO<T> {

    T create(T entity);

    T findById(int id);

    List<T> findAll();

    T update(T entity);

    void remove(int id);
}