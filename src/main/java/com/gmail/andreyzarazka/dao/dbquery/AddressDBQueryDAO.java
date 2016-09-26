package com.gmail.andreyzarazka.dao.dbquery;

import com.gmail.andreyzarazka.dao.AddressDAO;
import com.gmail.andreyzarazka.dao.ExceptionDAO;
import com.gmail.andreyzarazka.dao.FactoryDAO;
import com.gmail.andreyzarazka.domain.Address;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDBQueryDAO implements AddressDAO {
    private static Logger log = Logger.getLogger(AddressDBQueryDAO.class.getName());

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();

    @Override
    public List<Address> getAll() throws ExceptionDAO {
        List<Address> addresses = new ArrayList<>();
        Address address = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.address";
            connection = factoryDAO.getConnection();
            log.trace("Method getAll(): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getAll(): Create PreparedStatement");
            resultSet = statement.executeQuery();
            log.trace("Method getAll(): Get ResultSet");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String country = resultSet.getString(2);
                String street = resultSet.getString(3);
                int zipCode = resultSet.getInt(4);

                address = new Address();
                address.setId(id);
                address.setCountry(country);
                address.setStreet(street);
                address.setZipCode(zipCode);
                addresses.add(address);
            }
        } catch (SQLException e) {
            log.error("Method getAll(): Cannot read addresses\n", e);
            throw new ExceptionDAO("Cannot read addresses", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    log.trace("Method getAll(): ResultSet closed");
                }
            } catch (SQLException e) {
                log.error("Method getAll(): Cannot close ResultSet\n", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method getAll(): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method getAll(): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method getAll(): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method getAll(): Cannot close Connection\n", e);
            }
        }
        return addresses;
    }

    @Override
    public Address getById(int id) throws ExceptionDAO {
        Address address = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.address where id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method getById(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getById(int id): Create PreparedStatement");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            log.trace("Method getById(int id): Get ResultSet");

            while (resultSet.next()) {
                String country = resultSet.getString(2);
                String street = resultSet.getString(3);
                int zipCode = resultSet.getInt(4);

                address = new Address();
                address.setId(id);
                address.setCountry(country);
                address.setStreet(street);
                address.setZipCode(zipCode);
            }
        } catch (SQLException e) {
            log.error("Method getById(int id): I cannot get the address with the id " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the address with the specified " + id + " ", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    log.trace("Method getById(int id): ResultSet closed");
                }
            } catch (SQLException e) {
                log.error("Method getById(int id): Cannot close ResultSet\n", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method getById(int id): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method getById(int id): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method getById(int id): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method getById(int id): Cannot close Connection\n", e);
            }
        }
        return address;
    }

    @Override
    public boolean add(Address model) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO meloman_db.address (`id`,`country`, `street`, `zip_code`) VALUES (?, ?, ?, ?)";
            connection = factoryDAO.getConnection();
            log.trace("Method add(Address model): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method add(Address model): Create PreparedStatement");
            statement.setInt(1, model.getId());
            statement.setString(2, model.getCountry());
            statement.setString(3, model.getStreet());
            statement.setInt(4, model.getZipCode());
            statement.executeUpdate();
            log.error("Method add(Address model): Create address");
            current = true;
        } catch (SQLException e) {
            log.error("Method add(Address model): Cannot create address\n", e);
            throw new ExceptionDAO("Cannot create address", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method add(Address model): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method add(Address model): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method add(Address model): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method add(Address model): Cannot close Connection\n", e);
            }
        }
        return current;
    }

    @Override
    public boolean update(Address model) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE meloman_db.address SET country = ?, street = ?, zip_code = ? WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method update(Address model): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method update(Address model): Create PreparedStatement");
            statement.setString(1, model.getCountry());
            statement.setString(2, model.getStreet());
            statement.setInt(3, model.getZipCode());
            statement.setInt(4, model.getId());
            statement.executeUpdate();
            log.trace("Method update(Address model): Update address");
            current = true;
        } catch (SQLException e) {
            log.error("Method update(Address model): Cannot update address\n", e);
            throw new ExceptionDAO("Method updateAddress(Address address, int id): Cannot update address", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method update(Address model): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method update(Address model): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method update(Address model): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method update(Address model): Cannot close Connection\n", e);
            }
        }
        return current;
    }

    @Override
    public boolean delete(int id) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM meloman_db.address WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method delete(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method delete(int id): Create PreparedStatement");
            statement.setInt(1, id);
            statement.executeUpdate();
            log.error("Method delete(int id): Delete address");
            current = true;
        } catch (SQLException e) {
            log.error("Method delete(int id): I cannot delete the address with the id " + id + "\n ", e);
            throw new ExceptionDAO("I cannot delete the address with the id " + id + " ", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method delete(int id): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method delete(int id): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method delete(int id): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method delete(int id): Cannot close Connection\n", e);
            }
        }
        return current;
    }
}
