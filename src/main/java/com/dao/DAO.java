package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {
    
    public abstract ArrayList<T> select(T entity) throws SQLException;

    public abstract int add(T entity) throws SQLException;
    
    public abstract int delete(String query) throws SQLException;

    public abstract int modify(T entidad, String query) throws SQLException;

}
