package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.ConnectorDB;
import by.training.cafeproject.dao.OrderDao;
import by.training.cafeproject.entity.Food;
import by.training.cafeproject.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private Connection connection = ConnectorDB.getConnection();

    @Override
    public Integer create(Order entity) {
        try {
            String sql = "INSERT INTO orders (id, worker_id, client_id, client_name, food_id) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setInt(2, entity.getWorkerId());
            statement.setInt(3, entity.getClientId());
            statement.setString(4, entity.getClientName());
            statement.setInt(5, entity.getFoodId());
            statement.executeUpdate();
            statement.close();
            return 1;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public Order read(Integer id) {
        try {
            String sql = "SELECT worker_id, client_id, client_name, food_id FROM orders WHERE id = ?";
            Order order = new Order();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order.setId(id);
                order.setWorkerId(rs.getInt("worker_id"));
                order.setClientId(rs.getInt("client_id"));
                order.setClientName(rs.getString("client_name"));
                order.setFoodId(rs.getInt("food_id"));
            }
            ps.close();
            return order;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void update(Order entity) {
        try {
            String sql = "UPDATE orders SET worker_id = ?, client_id = ?, client_name = ?, food_id = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(5, entity.getId());
            statement.setInt(1, entity.getWorkerId());
            statement.setInt(2, entity.getClientId());
            statement.setString(3, entity.getClientName());
            statement.setInt(4, entity.getFoodId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Order> read() {
        try {
            String sql = "SELECT id, worker_id, client_id, client_name, food_id FROM orders";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setWorkerId(rs.getInt("worker_id"));
                order.setClientId(rs.getInt("client_id"));
                order.setClientName(rs.getString("client_name"));
                order.setFoodId(rs.getInt("food_id"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM orders WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void delete(Order entity) {
        try {
            String sql = "DELETE FROM orders WHERE food_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public List<Order> readByClientId(Integer clientId) {
        try {
            String sql = "SELECT id, worker_id, client_name, food_id FROM orders WHERE client_id = ?";
            List<Order> orders = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setClientId(clientId);
                order.setId(rs.getInt("id"));
                order.setWorkerId(rs.getInt("worker_id"));
                order.setClientName(rs.getString("client_name"));
                order.setFoodId(rs.getInt("food_id"));
                orders.add(order);
            }
            ps.close();
            return orders;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Order> readByClientName(String name) {
        try {
            String sql = "SELECT id, worker_id, client_id, food_id FROM orders WHERE client_name = ?";
            List<Order> orders = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setClientName(name);
                order.setId(rs.getInt("id"));
                order.setWorkerId(rs.getInt("worker_id"));
                order.setClientId(rs.getInt("client_id"));
                order.setFoodId(rs.getInt("food_id"));
                orders.add(order);
            }
            ps.close();
            return orders;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Order> readByFoodId(Integer foodId) {
        try {
            String sql = "SELECT id, worker_id, client_id, client_name FROM orders WHERE food_id = ?";
            List<Order> orders = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, foodId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setFoodId(foodId);
                order.setId(rs.getInt("id"));
                order.setWorkerId(rs.getInt("worker_id"));
                order.setClientId(rs.getInt("client_id"));
                order.setClientName(rs.getString("client_name"));
                orders.add(order);
            }
            ps.close();
            return orders;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<Order> readByWorkerId(Integer workerId) {
        try {
            String sql = "SELECT id, client_id, client_name, food_id FROM orders WHERE worker_id = ?";
            List<Order> orders = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, workerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setWorkerId(workerId);
                order.setId(rs.getInt("id"));
                order.setClientId(rs.getInt("client_id"));
                order.setClientName(rs.getString("client_name"));
                order.setFoodId(rs.getInt("food_id"));
                orders.add(order);
            }
            ps.close();
            return orders;
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void deleteByClientId(Integer clientId) {
        try {
            String sql = "DELETE FROM orders WHERE client_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, clientId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteByClientName(String name) {
        try {
            String sql = "DELETE FROM orders WHERE client_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteByWorkerId(Integer workerId) {
        try {
            String sql = "DELETE FROM orders WHERE worker_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, workerId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
