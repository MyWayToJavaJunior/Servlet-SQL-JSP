package com.gmail.andreyzarazka.dao.dbquery;

import com.gmail.andreyzarazka.dao.ExceptionDAO;
import com.gmail.andreyzarazka.dao.FactoryDAO;
import com.gmail.andreyzarazka.dao.UserDAO;
import com.gmail.andreyzarazka.domain.Address;
import com.gmail.andreyzarazka.domain.MusicType;
import com.gmail.andreyzarazka.domain.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBQueryDAO implements UserDAO {
    private static Logger log = Logger.getLogger(UserDBQueryDAO.class.getName());

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();

    @Override
    public List<User> getAll() throws ExceptionDAO {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT id, login, password, first_name, last_name, age FROM meloman_db.users";
            connection = factoryDAO.getConnection();
            log.trace("Method getAll(): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getAll(): Create PreparedStatement");
            resultSet = statement.executeQuery();
            log.trace("Method getAll(): Get ResultSet");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                int age = resultSet.getInt(6);

                User user = new User(id, login, password, firstName, lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Method getAll(): Cannot read users\n", e);
            throw new ExceptionDAO("Cannot read users", e);
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
        return users;
    }

    @Override
    public List<User> getAllFullUsers() throws ExceptionDAO {
        List<User> fullUsers = new ArrayList<>();
        User fullUser = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.users";
            connection = factoryDAO.getConnection();
            log.trace("Method getAllFullUsers(): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getAllFullUsers(): Create PreparedStatement");
            resultSet = statement.executeQuery();
            log.trace("Method getAllFullUsers(): Get ResultSet");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                int age = resultSet.getInt(6);
                int roleId = resultSet.getInt(7);

                fullUser = new User(id, login, password, firstName, lastName, age, roleId);
                fullUser.setRole(factoryDAO.getRoleDAO().getById(roleId));
                fullUser.setAddress(factoryDAO.getAddressDAO().getById(id));
                fullUser.setMusicTypes(getUsersHasMusicType(id));
                fullUsers.add(fullUser);
            }
        } catch (SQLException e) {
            log.error("Method getAllFullUsers(): Cannot read full users\n", e);
            throw new ExceptionDAO("Cannot read full users", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    log.trace("Method getAllFullUsers(): ResultSet closed");
                }
            } catch (SQLException e) {
                log.error("Method getAllFullUsers(): Cannot close ResultSet\n", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method getAllFullUsers(): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method getAllFullUsers(): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method getAllFullUsers(): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method getAllFullUsers(): Cannot close Connection\n", e);
            }
        }
        return fullUsers;
    }

    @Override
    public User getById(int id) throws ExceptionDAO {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT id, login, password, first_name, last_name, age FROM meloman_db.users WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method getById(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getById(int id): Create PreparedStatement");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            log.trace("Method getById(int id): Get ResultSet");

            while (resultSet.next()) {
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                int age = resultSet.getInt(6);

                user = new User(id, login, password, firstName, lastName, age);
            }
        } catch (SQLException e) {
            log.error("Method getById(int id): I cannot get the user with the specified " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the user with the specified " + id + " ", e);
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
        return user;
    }

    @Override
    public User getByFullUserId(int id) throws ExceptionDAO {
        User fullUser = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.users WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method getByFullUserId(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getByFullUserId(int id): Create PreparedStatement");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            log.trace("Method getByFullUserId(int id): Get ResultSet");

            while (resultSet.next()) {
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                int age = resultSet.getInt(6);
                int roleId = resultSet.getInt(7);

                fullUser = new User(id, login, password, firstName, lastName, age, roleId);
                fullUser.setRole(factoryDAO.getRoleDAO().getById(roleId));
                fullUser.setAddress(factoryDAO.getAddressDAO().getById(id));
                fullUser.setMusicTypes(getUsersHasMusicType(id));
            }
        } catch (SQLException e) {
            log.error("Method getByFullUserId(int id): I cannot get the full user with the specified " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the user with the specified " + id + " ", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    log.trace("Method getByFullUserId(int id): ResultSet closed");
                }
            } catch (SQLException e) {
                log.error("Method getByFullUserId(int id): Cannot close ResultSet\n", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method getByFullUserId(int id): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method getByFullUserId(int id): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method getByFullUserId(int id): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method getByFullUserId(int id): Cannot close Connection\n", e);
            }
        }
        return fullUser;
    }

    @Override
    public boolean add(User user) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "INSERT INTO meloman_db.users (`login`, `password`, `first_name`, `last_name`, `age`, `role_id`) VALUES (?, ?, ?, ?, ?, ?)";
            connection = factoryDAO.getConnection();
            log.trace("Method add(User user): Open Connection");
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            log.trace("Method add(User user): Create PreparedStatement");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setInt(6, user.getRoleId());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            log.trace("Method add(User user): Get ResultSet");
            resultSet.next();
            int userId = resultSet.getInt(1);
            user.setId(userId);

            Address address = user.getAddress();
            address.setId(userId);
            factoryDAO.getAddressDAO().createAddress(address, user.getId());

            if (user.getMusicTypes() != null) {
                createUsersHasMusicType(user.getMusicTypes(), user.getId());
            }
            log.trace("Method add(User user): Create user" + user.getLogin());
        } catch (SQLException e) {
            log.error("Method add(User user): Cannot create user\n", e);
            throw new ExceptionDAO("Cannot create user", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    log.trace("Method add(User user): ResultSet closed");
                }
            } catch (SQLException e) {
                log.error("Method add(User user): Cannot close ResultSet\n", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method add(User user): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method add(User user): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method add(User user): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method add(User user): Cannot close Connection\n", e);
            }
        }
        return current;
    }

    @Override
    public boolean update(User user) throws ExceptionDAO {
        boolean current = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE meloman_db.users SET login = ?, password = ?, first_name = ?, last_name = ?, age = ?, role_id = ? WHERE id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method update(User user): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method update(User user): Create PreparedStatement");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setInt(6, user.getRoleId());
            statement.setInt(7, user.getId());
            statement.executeUpdate();

            if (statement.executeUpdate() == 1) {
                current = true;
            }

            factoryDAO.getAddressDAO().updateAddress(user.getAddress(), user.getId());
            deleteUsersHasMusicType(user.getId());
            if (user.getMusicTypes() != null) {
                createUsersHasMusicType(user.getMusicTypes(), user.getId());
            }
            log.trace("Method update(User user): Update user");
        } catch (SQLException e) {
            log.error("Method update(User user): Cannot update user\n", e);
            throw new ExceptionDAO("Cannot update user", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method update(User user): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method update(User user): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method update(User user): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method update(User user): Cannot close Connection\n", e);
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
            log.trace("Method delete(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method delete(int id): Create PreparedStatement");
            statement.setInt(1, id);
            if (statement.executeUpdate() == 1) {
                factoryDAO.getAddressDAO().delete(id);
                deleteUsersHasMusicType(id);
                current = true;
            }
            log.trace("Method delete(int id): User delete");
        } catch (SQLException e) {
            log.error("Method delete(int id): I cannot delete user with the id = " + id + "\n ", e);
            throw new ExceptionDAO("I cannot delete user with the id = " + id + " ", e);
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

    private void createUsersHasMusicType(List<MusicType> musicTypes, int id) throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement statement = null;

        for (MusicType musicType : musicTypes) {
            try {
                String sql = "INSERT INTO meloman_db.users_has_music_type (`users_id`, `music_type_id`) VALUES (?, ?)";
                connection = factoryDAO.getConnection();
                log.trace("Method createUsersHasMusicType(List<MusicType> musicTypes, int id): Open Connection");
                statement = connection.prepareStatement(sql);
                log.trace("Method createUsersHasMusicType(List<MusicType> musicTypes, int id): Create PreparedStatement");
                statement.setInt(1, id);
                statement.setInt(2, musicType.getId());
                statement.executeUpdate();
                log.trace("Method createUsersHasMusicType(List<MusicType> musicTypes, int id): Create UsersHasMusicType");
            } catch (SQLException e) {
                log.error("Method createUsersHasMusicType(List<MusicType> musicTypes, int id): Cannot create UsersHasMusicType\n", e);
                throw new ExceptionDAO("Cannot create UsersHasMusicType", e);
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                        log.trace("Method createUsersHasMusicType(List<MusicType> musicTypes, int id): PreparedStatement closed");
                    }
                } catch (SQLException e) {
                    log.error("Method createUsersHasMusicType(List<MusicType> musicTypes, int id): Cannot close PreparedStatement\n", e);
                }
                try {
                    if (connection != null) {
                        connection.close();
                        log.trace("Method createUsersHasMusicType(List<MusicType> musicTypes, int id): Connection closed");
                    }
                } catch (SQLException e) {
                    log.error("Method createUsersHasMusicType(List<MusicType> musicTypes, int id): Cannot close Connection\n", e);
                }
            }
        }
    }

    private List<MusicType> getUsersHasMusicType(int id) throws ExceptionDAO {
        List<MusicType> musicTypes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.users_has_music_type WHERE users_id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method getUsersHasMusicType(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method getUsersHasMusicType(int id): Create PreparedStatement");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            log.trace("Method getUsersHasMusicType(int id): Get ResultSet");

            while (resultSet.next()) {
                int musicTypeId = resultSet.getInt(2);
                musicTypes.add(factoryDAO.getMusicTypeDAO().getById(musicTypeId));
            }
            log.trace("Method getUsersHasMusicType(int id): Found UsersHasMusicType with the id " + id);
        } catch (SQLException e) {
            log.error("Method getUsersHasMusicType(int id): I cannot get the UsersHasMusicType with the id " + id + "\n ", e);
            throw new ExceptionDAO("I cannot get the UsersHasMusicType with the id " + id + " ", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    log.trace("Method getUsersHasMusicType(int id): ResultSet closed");
                }
            } catch (SQLException e) {
                log.error("Method getUsersHasMusicType(int id): Cannot close ResultSet\n", e);
            }
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method getUsersHasMusicType(int id): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method getUsersHasMusicType(int id): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method getUsersHasMusicType(int id): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method getUsersHasMusicType(int id): Cannot close Connection\n", e);
            }
        }
        return musicTypes;
    }

    private void deleteUsersHasMusicType(int id) throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM meloman_db.users_has_music_type WHERE users_id = ?";
            connection = factoryDAO.getConnection();
            log.trace("Method deleteUsersHasMusicType(int id): Open Connection");
            statement = connection.prepareStatement(sql);
            log.trace("Method deleteUsersHasMusicType(int id): Create PreparedStatement");
            statement.setInt(1, id);
            statement.executeUpdate();
            log.error("Method deleteUsersHasMusicType(int id): Delete UsersHasMusicType");
        } catch (SQLException e) {
            log.error("Method deleteUsersHasMusicType(int id): Cannot delete UsersHasMusicType\n", e);
            throw new ExceptionDAO("Cannot delete UsersHasMusicType", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    log.trace("Method deleteUsersHasMusicType(int id): PreparedStatement closed");
                }
            } catch (SQLException e) {
                log.error("Method deleteUsersHasMusicType(int id): Cannot close PreparedStatement\n", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                    log.trace("Method deleteUsersHasMusicType(int id): Connection closed");
                }
            } catch (SQLException e) {
                log.error("Method deleteUsersHasMusicType(int id): Cannot close Connection\n", e);
            }
        }
    }
}
