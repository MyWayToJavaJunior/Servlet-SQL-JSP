package com.gmail.andreyzarazka.dao;

import com.gmail.andreyzarazka.domain.Address;

public interface AddressDAO extends AbstractModelDAO<Address> {

    Address createAddress(Address address, int id) throws ExceptionDAO;

    boolean updateAddress(Address address, int id) throws ExceptionDAO;
}
