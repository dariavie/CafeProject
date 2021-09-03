package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.ConnectorDB;
import by.training.cafeproject.dao.UserDao;
import by.training.cafeproject.entity.Ingredient;
import by.training.cafeproject.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Connection connection = ConnectorDB.getConnection();

    @Override
    public Integer create(User entity) {
        try {
            String sql = "INSERT INTO users (id, login, password, role) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getLogin());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, entity.getRoleNumber());
            statement.executeUpdate();
            statement.close();
            return 1;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public User read(Integer id) {
        try {
            String sql = "SELECT login, password, role FROM users WHERE id = ?";
            User user = new User();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(id);
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
            }
            ps.close();
            return user;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void update(User entity) {
        try {
            String sql = "UPDATE users SET login = ?, password = ?, role = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(4, entity.getId());
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getRoleNumber());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public User read(String login, String password) {
        try {
            String sql = "SELECT id, role FROM users WHERE login = ? and password = ?";
            User user = new User();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setLogin(login);
                user.setPassword(password);
                user.setId(rs.getInt("id"));
                user.setRole(rs.getInt("role"));
            }
            ps.close();
            return user;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<User> read() {
        try {
            String sql = "SELECT id, login, password, role FROM users";
            Statement statement = connection.createStatement();
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
            e.getMessage();
            return null;
        }
    }

    @Override
    public void delete(String login, String password) {
        try {
            String sql = "DELETE FROM users WHERE login = ? and password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void delete(User entity) {
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
