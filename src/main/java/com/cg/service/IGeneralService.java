package com.cg.service;

import java.util.List;

public interface IGeneralService<T> {
    boolean existsById (int id);
    List<T> findAll();
    List<T> searchAll(String query);
    boolean create(T t);
    boolean update(T t);
    boolean disable(int id);
    boolean reActive(int id);
}
