package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.UserInfoDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {

    @Override
    public void create(UserInfo entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO user_info (id, surname, name, phone, email) VALUES (?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getPhone());
            statement.setString(5, entity.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public UserInfo read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT surname, name, phone, email FROM user_info WHERE id = ?";
            UserInfo userInfo = new UserInfo();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userInfo.setUserId(new User(id));
                userInfo.setSurname(rs.getString("surname"));
                userInfo.setName(rs.getString("name"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setEmail(rs.getString("email"));
            }
            return userInfo;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void update(UserInfo entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE user_info SET name = ?, surname = ?, phone = ?, email = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(5, entity.getId());
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getPhone());
            statement.setString(4, entity.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<UserInfo> read() throws DaoException {
        Statement statement = null;
        try {
            String sql = "SELECT id, name, surname, phone, email FROM user_info";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<UserInfo> userInfos = new ArrayList<>();
            while (rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(new User(rs.getInt("id")));
                userInfo.setName(rs.getString("name"));
                userInfo.setSurname(rs.getString("surname"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setEmail(rs.getString("email"));
                userInfos.add(userInfo);
            }
            return userInfos;
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
            String sql = "DELETE FROM foods WHERE id = ?";
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
    public void delete(UserInfo entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM foods WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<UserInfo> readByName(String name) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, surname, phone, email FROM user_info WHERE name = ?";
            List<UserInfo> userInfos = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setName(name);
                userInfo.setUserId(new User(rs.getInt("id")));
                userInfo.setSurname(rs.getString("surname"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setEmail(rs.getString("email"));
                userInfos.add(userInfo);
            }
            return userInfos;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<UserInfo> readBySurname(String surname) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, name, phone, email FROM user_info WHERE surname = ?";
            List<UserInfo> userInfos = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setString(1, surname);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setName(surname);
                userInfo.setUserId(new User(rs.getInt("id")));
                userInfo.setName(rs.getString("name"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setEmail(rs.getString("email"));
                userInfos.add(userInfo);
            }
            return userInfos;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public UserInfo readByPhone(String phone) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, name, surname, email FROM user_info WHERE phone = ?";
            UserInfo userInfo = new UserInfo();
            statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userInfo.setPhone(phone);
                userInfo.setUserId(new User(rs.getInt("id")));
                userInfo.setName(rs.getString("name"));
                userInfo.setSurname(rs.getString("surname"));
                userInfo.setEmail(rs.getString("email"));
            }
            return userInfo;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public UserInfo readByEmail(String email) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, name, surname, phone FROM user_info WHERE email = ?";
            UserInfo userInfo = new UserInfo();
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userInfo.setEmail(email);
                userInfo.setUserId(new User(rs.getInt("id")));
                userInfo.setName(rs.getString("name"));
                userInfo.setSurname(rs.getString("surname"));
                userInfo.setPhone(rs.getString("phone"));
            }
            return userInfo;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByPhone(String phone) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM user_info WHERE phone = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByEmail(String email) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM user_info WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
