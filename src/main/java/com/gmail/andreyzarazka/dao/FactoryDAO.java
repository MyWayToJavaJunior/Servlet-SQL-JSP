package com.gmail.andreyzarazka.dao;

import com.gmail.andreyzarazka.dao.dbquery.AddressDBQueryDAO;
import com.gmail.andreyzarazka.dao.dbquery.MusicTypeDBQueryDAO;
import com.gmail.andreyzarazka.dao.dbquery.RoleDBQueryDAO;
import com.gmail.andreyzarazka.dao.dbquery.UserDBQueryDAO;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FactoryDAO {
    private static Logger log = Logger.getLogger(FactoryDAO.class.getName());
    private static FactoryDAO factoryDAO;

    private String user;
    private String password;
    private String url;
    private String driver;

    public static FactoryDAO getInstance() {
        if (factoryDAO == null) {
            factoryDAO = new FactoryDAO();
        }
        return factoryDAO;
    }

    private FactoryDAO() {
        loadProperties();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("Driver not found\n", e);
        }
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(FactoryDAO.class.getResourceAsStream("/db.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (IOException e) {
            log.error("Can't read the file\n", e);
            throw new ExceptionDBProperties("Can't read the file ", e);
        }
    }

    public Connection getConnection() throws ExceptionDAO {
        try {
            log.info("Driver manager get connection");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            log.error("There is no database connection\n", e);
            throw new ExceptionDAO("There is no database connection", e);
        }
    }

    public UserDAO getUserDAO() {
        return new UserDBQueryDAO();
    }

    public RoleDAO getRoleDAO() {
        return new RoleDBQueryDAO();
    }

    public AddressDAO getAddressDAO() {
        return new AddressDBQueryDAO();
    }

    public MusicTypeDAO getMusicTypeDAO() {
        return new MusicTypeDBQueryDAO();
    }
}
