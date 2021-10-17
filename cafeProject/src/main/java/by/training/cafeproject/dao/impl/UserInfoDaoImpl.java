package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.UserInfoDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {
    private static final String SQL_CREATE = "INSERT INTO user_info (id, surname, name, phone, email) VALUES (?,?,?,?,?)";
    private static final String SQL_READ_BY_ID = "SELECT surname, name, phone, email FROM user_info WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE user_info SET name = ?, surname = ?, phone = ?, email = ? WHERE id = ?";
    private static final String SQL_READ_ALL = "SELECT id, name, surname, phone, email FROM user_info";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM user_info WHERE id = ?";
    private static final String SQL_READ_BY_PHONE = "SELECT id, name, surname, email FROM user_info WHERE phone = ?";
    private static final String SQL_READ_BY_EMAIL = "SELECT id, name, surname, phone FROM user_info WHERE email = ?";

    @Override
    public Integer create(UserInfo entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, entity.getUserId().getId());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getName());
            statement.setString(4, entity.getPhone());
            statement.setString(5, entity.getEmail());
            statement.executeUpdate();
            entity.setId(entity.getUserId().getId());
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//
//            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
            return entity.getId();
        }
    }

    @Override
    public UserInfo read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            UserInfo userInfo = new UserInfo();
            statement = connection.prepareStatement(SQL_READ_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userInfo.setId(id);
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
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(5, entity.getUserId().getId());
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
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_READ_ALL);
            List<UserInfo> userInfos = new ArrayList<>();
            while (rs.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(rs.getInt("id"));
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
            statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
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
            UserInfo userInfo = new UserInfo();
            statement = connection.prepareStatement(SQL_READ_BY_PHONE);
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userInfo.setId(rs.getInt("id"));
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
            UserInfo userInfo = new UserInfo();
            statement = connection.prepareStatement(SQL_READ_BY_EMAIL);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userInfo.setId(rs.getInt("id"));
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
}
