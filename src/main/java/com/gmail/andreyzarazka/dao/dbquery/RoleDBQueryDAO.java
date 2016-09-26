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
            log.trace("Method getAll(): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getAll(): Create PreparedStatement");
            resultSet = statement.executeQuery();
            log.trace("Method getAll(): Get ResultSet");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String roleName = resultSet.getString(2);

                role = new Role();
                role.setId(id);
                role.setRoleName(roleName);
                roles.add(role);
            }
        } catch (SQLException e) {
            log.error("Method getAll(): Cannot read role\n", e);
            throw new ExceptionDAO("Cannot read role", e);
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
            log.trace("Method getById(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getById(int id): Create PreparedStatement");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            log.trace("Method getById(int id): Get ResultSet");

            while (resultSet.next()) {
                String roleName = resultSet.getString(2);
                role = new Role();
                role.setId(id);
                role.setRoleName(roleName);
            }
        } catch (SQLException e) {
            log.error("Method getById(int id): I cannot get the role with the id " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the role with the specified " + id + " ", e);
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
            return role;
        }
    }

    @Override
    public boolean add(Role model) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO meloman_db.role (`role_name`) VALUES (?)";
            connection = factoryDAO.getConnection();
            log.trace("Method add(Role model): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method add(Role model): Create PreparedStatement");
            statement.setString(1, model.getRoleName());
            statement.executeUpdate();
            log.trace("Method add(Role model): Create role");
            current = true;
        } catch (SQLException e) {
            log.error("Method add(Role model): Cannot create role\n", e);
            throw new ExceptionDAO("Cannot create role", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method add(Role model): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method add(Role model): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method add(Role model): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method add(Role model): Cannot close Connection\n", e);
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
            log.trace("Method update(Role model): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method update(Role model): Create PreparedStatement");
            statement.setString(1, model.getRoleName());
            statement.executeUpdate();
            log.error("Method update(Role model): Update role");
            current = true;
        } catch (SQLException e) {
            log.error("Method update(Role model): Cannot update role\n", e);
            throw new ExceptionDAO("Cannot update role", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method update(Role model): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method update(Role model): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method update(Role model): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method update(Role model): Cannot close Connection\n", e);
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
            String sql = "DELETE FROM meloman_db.role WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method update(Role model): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method update(Role model): Create PreparedStatement");
            statement.setInt(1, id);
            statement.executeUpdate();
            log.trace("Method delete(int id): Delete role");
            current = true;
        } catch (SQLException e) {
            log.error("Method delete(int id): I cannot get the role with the id " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the role with the id " + id + " ", e);
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
