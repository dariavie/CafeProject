package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.UserDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.pool.ConnectionPool;
import by.training.cafeproject.domain.Role;
import by.training.cafeproject.domain.User;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final Logger userDaoImplLogger = Logger.getLogger(UserDaoImpl.class);
    private static final String SQL_CREATE = "INSERT INTO users (login, password, role) VALUES (?,?,?)";
    private static final String SQL_READ_BY_ID = "SELECT login, password, role FROM users WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE users SET login = ?, password = ?, role = ? WHERE id = ?";
    private static final String SQL_READ_BY_LOGIN_PASSWORD = "SELECT id, role FROM users WHERE login = ? and password = ?";
    private static final String SQL_READ_BY_LOGIN = "SELECT id, role, password FROM users WHERE login = ?";
    private static final String SQL_READ_ALL = "SELECT id, login, password, role FROM users";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE id = ?";

    @Override
    public Integer create(User entity) throws DaoException {
        userDaoImplLogger.info("came into create dao ");
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole().getIdentity());
            statement.executeUpdate();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                entity.setId(rs.getInt("id"));
            }
            userDaoImplLogger.info("rs executed, id: " + entity.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
            return entity.getId();
        }
    }

    @Override
    public User read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            User user = new User();
            statement = connection.prepareStatement(SQL_READ_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(id);
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.getByIdentity(rs.getInt("role")));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void update(User entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(4, entity.getId());
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRole().getIdentity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public User read(String login, String password) throws DaoException {
        PreparedStatement statement = null;
        try {
            User user = new User();
            statement = connection.prepareStatement(SQL_READ_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setLogin(login);
                user.setPassword(password);
                user.setId(rs.getInt("id"));
                user.setRole(Role.getByIdentity(rs.getInt("role")));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public User readByLogin(String login) throws DaoException {
        PreparedStatement statement = null;
        try {
            User user = new User();
            statement = connection.prepareStatement(SQL_READ_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setLogin(login);
                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.getByIdentity(rs.getInt("role")));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<User> read() throws DaoException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_READ_ALL);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.getByIdentity(rs.getInt("role")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closeStatement(statement);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
