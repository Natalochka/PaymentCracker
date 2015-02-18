package com.payment_cracker.api.dao.basic_level.basic_dao;


import com.payment_cracker.api.dao.exceptions.DbException;

import java.util.List;

/**
 * Created by Александр on 12/7/2014.
 */
public interface DAO<T, E> {
    public void add(T entity) throws DbException;
    public void update(T entity) throws DbException;
    public T getById(E id) throws DbException;
    public List<T> getAll() throws DbException;
}
