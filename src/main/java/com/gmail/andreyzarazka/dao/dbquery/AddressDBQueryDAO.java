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
            String sql = "SELECT * FROM meloman_db.address;";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            resultSet = statement.executeQuery();
            log.trace("Get ResultSet");

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
            log.error("Cannot read addresses\n", e);
            throw new ExceptionDAO("Cannot read addresses", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    log.trace("ResultSet closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close ResultSet\n", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Connection closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close Connection\n", e);
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
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            log.trace("Get ResultSet");

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
            log.error("I cannot get the address with the specified " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the address with the specified " + id + " ", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    log.trace("ResultSet closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close ResultSet\n", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Connection closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close Connection\n", e);
            }
        }
        return address;
    }

    @Override
    public boolean add(Address model) throws ExceptionDAO {
        return false;
    }

    @Override
    public boolean update(Address model) throws ExceptionDAO {
        return false;
    }

    @Override
    public boolean delete(int id) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM meloman_db.address WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setInt(1, id);
            if (statement.executeUpdate() == 1) {
                current = true;
            }
        } catch (SQLException e) {
            log.error("I cannot get the address with the specified " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the address with the specified " + id + " ", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Connection closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close Connection\n", e);
            }
        }
        return current;
    }

    @Override
    public Address createAddress(Address address, int id) throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO meloman_db.address (`id`,`country`, `street`, `zip_code`) VALUES (?, ?, ?, ?);";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setInt(1, id);
            statement.setString(2, address.getCountry());
            statement.setString(3, address.getStreet());
            statement.setInt(4, address.getZipCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Cannot create address\n", e);
            throw new ExceptionDAO("Cannot create address", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Connection closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close Connection\n", e);
            }
        }
        return address;
    }

    @Override
    public boolean updateAddress(Address address, int id) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE meloman_db.address SET country = ?, street = ?, zip_code = ? WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getStreet());;
            statement.setInt(3, address.getZipCode());
            statement.setInt(4, id);
            statement.executeUpdate();

            if (statement.executeUpdate() == 1) {
                current = true;
            }
        } catch (SQLException e) {
            log.error("Cannot update address\n", e);
            throw new ExceptionDAO("Cannot update address", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Connection closed");
                }
            } catch (SQLException e) {
                log.error("Cannot close Connection\n", e);
            }
        }
        return current;
    }
}
