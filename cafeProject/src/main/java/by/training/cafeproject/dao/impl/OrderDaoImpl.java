package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.OrderDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.Order;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

    @Override
    public void create(Order entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO orders (id, worker_id, client_id, client_name, food_id) VALUES (?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setInt(2, entity.getWorkerId().getId());
            statement.setInt(3, entity.getClientId().getId());
            statement.setString(4, entity.getClientName());
            statement.setInt(5, entity.getFoodId().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public Order read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT worker_id, client_id, client_name, food_id FROM orders WHERE id = ?";
            Order order = new Order();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                order.setId(id);
                order.setWorkerId(new Worker(rs.getInt("worker_id")));
                order.setClientId(new User(rs.getInt("client_id")));
                order.setClientName(rs.getString("client_name"));
                order.setFoodId(new Food(rs.getInt("food_id")));
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
            String sql = "UPDATE orders SET worker_id = ?, client_id = ?, client_name = ?, food_id = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(5, entity.getId());
            statement.setInt(1, entity.getWorkerId().getId());
            statement.setInt(2, entity.getClientId().getId());
            statement.setString(3, entity.getClientName());
            statement.setInt(4, entity.getFoodId().getId());
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
            String sql = "SELECT id, worker_id, client_id, client_name, food_id FROM orders";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setWorkerId(new Worker(rs.getInt("worker_id")));
                order.setClientId(new User(rs.getInt("client_id")));
                order.setClientName(rs.getString("client_name"));
                order.setFoodId(new Food(rs.getInt("food_id")));
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
            String sql = "DELETE FROM orders WHERE id = ?";
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
    public void delete(Order entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM orders WHERE food_id = ?";
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
    public List<Order> readByClientId(Integer clientId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, worker_id, client_name, food_id FROM orders WHERE client_id = ?";
            List<Order> orders = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setClientId(new User(clientId));
                order.setId(rs.getInt("id"));
                order.setWorkerId(new Worker(rs.getInt("worker_id")));
                order.setClientName(rs.getString("client_name"));
                order.setFoodId(new Food(rs.getInt("food_id")));
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
    public List<Order> readByClientName(String name) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, worker_id, client_id, food_id FROM orders WHERE client_name = ?";
            List<Order> orders = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setClientName(name);
                order.setId(rs.getInt("id"));
                order.setWorkerId(new Worker(rs.getInt("worker_id")));
                order.setClientId(new User(rs.getInt("client_id")));
                order.setFoodId(new Food(rs.getInt("food_id")));
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
    public List<Order> readByFoodId(Integer foodId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, worker_id, client_id, client_name FROM orders WHERE food_id = ?";
            List<Order> orders = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, foodId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setFoodId(new Food(foodId));
                order.setId(rs.getInt("id"));
                order.setWorkerId(new Worker(rs.getInt("worker_id")));
                order.setClientId(new User(rs.getInt("client_id")));
                order.setClientName(rs.getString("client_name"));
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
    public List<Order> readByWorkerId(Integer workerId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, client_id, client_name, food_id FROM orders WHERE worker_id = ?";
            List<Order> orders = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, workerId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setWorkerId(new Worker(workerId));
                order.setId(rs.getInt("id"));
                order.setClientId(new User(rs.getInt("client_id")));
                order.setClientName(rs.getString("client_name"));
                order.setFoodId(new Food(rs.getInt("food_id")));
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
            String sql = "DELETE FROM orders WHERE client_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByWorkerId(Integer workerId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM orders WHERE worker_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, workerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
