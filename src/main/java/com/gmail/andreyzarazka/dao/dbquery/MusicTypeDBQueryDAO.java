package com.gmail.andreyzarazka.dao.dbquery;

import com.gmail.andreyzarazka.dao.ExceptionDAO;
import com.gmail.andreyzarazka.dao.FactoryDAO;
import com.gmail.andreyzarazka.dao.MusicTypeDAO;
import com.gmail.andreyzarazka.domain.MusicType;
import com.gmail.andreyzarazka.domain.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicTypeDBQueryDAO implements MusicTypeDAO {
    private static Logger log = Logger.getLogger(MusicTypeDBQueryDAO.class.getName());

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();

    @Override
    public List<MusicType> getAll() throws ExceptionDAO {
        List<MusicType> musicTypes = new ArrayList<>();
        MusicType musicType = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.music_type;";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            resultSet = statement.executeQuery();
            log.trace("Get ResultSet");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String musicTypeName = resultSet.getString(2);

                musicType = new MusicType();
                musicType.setId(id);
                musicType.setMusicTypeName(musicTypeName);
                musicTypes.add(musicType);
            }
        } catch (SQLException e) {
            log.error("Cannot read MusicType\n", e);
            throw new ExceptionDAO("Cannot read MusicType", e);
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
        return musicTypes;
    }

    @Override
    public MusicType getById(int id) throws ExceptionDAO {
        MusicType musicType = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.music_type where id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            log.trace("Get ResultSet");

            while (resultSet.next()) {
                String musicTypeName = resultSet.getString(2);
                musicType = new MusicType();
                musicType.setId(id);
                musicType.setMusicTypeName(musicTypeName);

            }
        } catch (SQLException e) {
            log.error("I cannot get the MusicType with the specified " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the MusicType with the specified " + id + " ", e);
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
            return musicType;
        }
    }

    @Override
    public boolean add(MusicType model) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO meloman_db.music_type (`type_name`) VALUES (?);";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setString(1, model.getMusicTypeName());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Cannot create MusicType\n", e);
            throw new ExceptionDAO("Cannot create MusicType", e);
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
    public boolean update(MusicType model) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE meloman_db.music_type SET type_name = ? WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Create PreparedStatement");
            statement.setString(1, model.getMusicTypeName());
            if (statement.executeUpdate() == 1) {
                current = true;
            }
        } catch (SQLException e) {
            log.error("Cannot update MusicType\n", e);
            throw new ExceptionDAO("Cannot update MusicType", e);
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
            log.error("I cannot get the MusicType with the specified " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the MusicType with the specified " + id + " ", e);
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
