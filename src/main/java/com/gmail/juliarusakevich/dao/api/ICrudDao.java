package com.gmail.juliarusakevich.dao.api;

import com.gmail.juliarusakevich.dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface ICrudDao<K, T> {

    T save(T entity) throws DAOException;

    List<T> findAll() throws DAOException;

    void update(Integer id, T entity) throws DAOException;

    boolean delete(Integer id) throws DAOException;

    Optional<T> findById(Integer id) throws DAOException;
}
