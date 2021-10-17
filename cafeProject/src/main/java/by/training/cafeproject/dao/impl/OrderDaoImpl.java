package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.OrderDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    private static final String SQL_CREATE = "INSERT INTO orders (client_id, price, status) VALUES (?,?,?)";
    private static final String SQL_CREATE_RETURN_ID = "SELECT id FROM orders WHERE client_id = ? and price = ? and status = ?";
    private static final String SQL_READ_BY_ID = "SELECT client_id, price, status FROM orders WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE orders SET client_id = ?, price = ?, status = ? WHERE id = ?";
    private static final String SQL_READ_ALL = "SELECT id, client_id, price, status FROM orders";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM orders WHERE id = ?";
    private static final String SQL_READ_BY_CLIENT_ID = "SELECT id, price, status FROM orders WHERE client_id = ?";
    private static final String SQL_DELETE_BY_CLIENT_ID = "DELETE FROM orders WHERE client_id = ?";

    @Override
    public Integer create(Order entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, entity.getClientId().getId());
            statement.setDouble(2, entity.getPrice());
            statement.setInt(3, entity.getOrderStatus().getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_CREATE_RETURN_ID);
            statement.setInt(1, entity.getClientId().getId());
            statement.setDouble(2, entity.getPrice());
            statement.setInt(3, entity.getOrderStatus().getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                entity.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
            return entity.getId();
        }
    }

    @Override
    public Order read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            Order order = new Order();
            statement = connection.prepareStatement(SQL_READ_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                order.setId(id);
                order.setClientId(new UserInfo(rs.getInt("client_id")));
                order.setPrice(rs.getDouble("price"));
                order.setOrderStatus(OrderStatus.getById(rs.getInt("status")));
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void update(Order entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(3, entity.getId());
            statement.setInt(1, entity.getClientId().getId());
            statement.setDouble(2, entity.getPrice());
            statement.setInt(3, entity.getOrderStatus().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<Order> read() throws DaoException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_READ_ALL);
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setClientId(new UserInfo(rs.getInt("client_id")));
                order.setPrice(rs.getDouble("price"));
                order.setOrderStatus(OrderStatus.getById(rs.getInt("status")));
                orders.add(order);
            }
            return orders;
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
    public List<Order> readByClientId(Integer clientId) throws DaoException {
        PreparedStatement statement = null;
        try {
            List<Order> orders = new ArrayList<>();
            statement = connection.prepareStatement(SQL_READ_BY_CLIENT_ID);
            statement.setInt(1, clientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setClientId(new UserInfo(clientId));
                order.setId(rs.getInt("id"));
                order.setPrice(rs.getDouble("price"));
                order.setOrderStatus(OrderStatus.getById(rs.getInt("status")));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByClientId(Integer clientId) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_BY_CLIENT_ID);
            statement.setInt(1, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
