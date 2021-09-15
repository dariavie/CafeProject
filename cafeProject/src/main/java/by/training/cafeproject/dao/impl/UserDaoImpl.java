package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.UserDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public void create(User entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO users (id, login, password, role) VALUES (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getLogin());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, entity.getRoleNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public User read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT login, password, role FROM users WHERE id = ?";
            User user = new User();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(id);
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
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
            String sql = "UPDATE users SET login = ?, password = ?, role = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(4, entity.getId());
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRoleNumber());
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
            String sql = "SELECT id, role FROM users WHERE login = ? and password = ?";
            User user = new User();
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user.setLogin(login);
                user.setPassword(password);
                user.setId(rs.getInt("id"));
                user.setRole(rs.getInt("role"));
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
            String sql = "SELECT id, login, password, role FROM users";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
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
    public void delete(String login, String password) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM users WHERE login = ? and password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void delete(User entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
