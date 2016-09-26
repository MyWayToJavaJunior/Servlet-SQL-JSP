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
            String sql = "SELECT * FROM meloman_db.music_type";
            connection = factoryDAO.getConnection();
            log.trace("Method getAll(): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getAll(): Create PreparedStatement");
            resultSet = statement.executeQuery();
            log.trace("Method getAll(): Get ResultSet");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String musicTypeName = resultSet.getString(2);

                musicType = new MusicType();
                musicType.setId(id);
                musicType.setMusicTypeName(musicTypeName);
                musicTypes.add(musicType);
            }
        } catch (SQLException e) {
            log.error("Method getAll(): Cannot read MusicType\n", e);
            throw new ExceptionDAO("Cannot read MusicType", e);
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
            log.trace("Method getById(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getById(int id): Create PreparedStatement");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            log.trace("Method getById(int id): Get ResultSet");

            while (resultSet.next()) {
                String musicTypeName = resultSet.getString(2);
                musicType = new MusicType();
                musicType.setId(id);
                musicType.setMusicTypeName(musicTypeName);
            }
        } catch (SQLException e) {
            log.error("Method getById(int id): I cannot get the MusicType with the id " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the MusicType with the id " + id + " ", e);
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
            return musicType;
        }
    }

    @Override
    public boolean add(MusicType model) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO meloman_db.music_type (`type_name`) VALUES (?)";
            connection = factoryDAO.getConnection();
            log.trace("Method add(MusicType model): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method add(MusicType model): Create PreparedStatement");
            statement.setString(1, model.getMusicTypeName());
            statement.executeUpdate();
            log.trace("Method add(MusicType model): Create MusicType");
            current = true;
        } catch (SQLException e) {
            log.error("Method add(MusicType model): Cannot create MusicType\n", e);
            throw new ExceptionDAO("Method add(MusicType model): Cannot create MusicType", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method add(MusicType model): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method add(MusicType model): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method add(MusicType model): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method add(MusicType model): Cannot close Connection\n", e);
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
            log.trace("Method update(MusicType model): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method update(MusicType model): Create PreparedStatement");
            statement.setString(1, model.getMusicTypeName());
            statement.executeUpdate();
            log.error("Method update(MusicType model): Update MusicType");
            current = true;
        } catch (SQLException e) {
            log.error("Method update(MusicType model): Cannot update MusicType\n", e);
            throw new ExceptionDAO("Cannot update MusicType", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method update(MusicType model): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method update(MusicType model): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method update(MusicType model): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method update(MusicType model): Cannot close Connection\n", e);
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
            String sql = "DELETE FROM meloman_db.music_type WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method delete(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method delete(int id): Create PreparedStatement");
            statement.setInt(1, id);
            statement.executeUpdate();
            log.trace("Method delete(int id): Delete MusicType");
            current = true;
        } catch (SQLException e) {
            log.error("Method delete(int id): I cannot get the MusicType with the id " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the MusicType with the id " + id + " ", e);
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
