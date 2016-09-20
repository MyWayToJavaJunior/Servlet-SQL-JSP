package com.gmail.andreyzarazka.dao;

import com.gmail.andreyzarazka.domain.User;

import java.util.List;

public interface UserDAO extends AbstractModelDAO<User> {

    List<User> getAllFullUsers() throws ExceptionDAO;

    User getByFullUserId(int id) throws ExceptionDAO;
}

