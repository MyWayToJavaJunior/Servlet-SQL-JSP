package com.gmail.andreyzarazka.dao.dbquery;

import com.gmail.andreyzarazka.dao.ExceptionDAO;
import com.gmail.andreyzarazka.dao.FactoryDAO;
import com.gmail.andreyzarazka.dao.RoleDAO;
import com.gmail.andreyzarazka.domain.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDBQueryDAO implements RoleDAO {
    private static Logger log = Logger.getLogger(RoleDBQueryDAO.class.getName());

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();

    @Override
    public List<Role> getAll() throws ExceptionDAO {
        List<Role> roles = new ArrayList<>();
        Role role = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.role;";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            resultSet = statement.executeQuery();
            log.trace("Get ResultSet");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String roleName = resultSet.getString(2);

                role = new Role();
                role.setId(id);
                role.setRoleName(roleName);
                roles.add(role);
            }
        } catch (SQLException e) {
            log.error("Cannot read role\n", e);
            throw new ExceptionDAO("Cannot read role", e);
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
        return roles;
    }

    @Override
    public Role getById(int id) throws ExceptionDAO {
        Role role = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.role where id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            log.trace("Get ResultSet");

            while (resultSet.next()) {
                String roleName = resultSet.getString(2);
                role = new Role();
                role.setId(id);
                role.setRoleName(roleName);
            }
        } catch (SQLException e) {
            log.error("I cannot get the role with the specified " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the role with the specified " + id + " ", e);
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
            return role;
        }
    }

    @Override
    public boolean add(Role model) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO meloman_db.role (`role_name`) VALUES (?);";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setString(1, model.getRoleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Cannot create role\n", e);
            throw new ExceptionDAO("Cannot create role", e);
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
    public boolean update(Role model) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE meloman_db.role SET role_name = ? WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setString(1, model.getRoleName());
            if (statement.executeUpdate() >= 1) {
                current = true;
            }
        } catch (SQLException e) {
            log.error("Cannot update role\n", e);
            throw new ExceptionDAO("Cannot update role", e);
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
    public boolean delete(int id) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM meloman_db.users WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setInt(1, id);
            if (statement.executeUpdate() >= 1) {
                current = true;
            }
        } catch (SQLException e) {
            log.error("I cannot get the role with the specified " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the role with the specified " + id + " ", e);
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
