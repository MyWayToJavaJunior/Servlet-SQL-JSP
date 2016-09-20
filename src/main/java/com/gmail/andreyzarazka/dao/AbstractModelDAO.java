package com.gmail.andreyzarazka.dao;

import com.gmail.andreyzarazka.domain.Model;

import java.util.List;

public interface AbstractModelDAO<T extends Model> {

    List<T> getAll() throws ExceptionDAO;

    T getById(int id) throws ExceptionDAO;

    boolean add(T model) throws ExceptionDAO;

    boolean update(T model) throws ExceptionDAO;

    boolean delete(int id) throws ExceptionDAO;
}