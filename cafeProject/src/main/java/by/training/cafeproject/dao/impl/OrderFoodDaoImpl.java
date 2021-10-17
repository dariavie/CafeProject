package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.OrderFoodDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.*;
import com.mysql.cj.log.Log;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderFoodDaoImpl extends BaseDaoImpl implements OrderFoodDao {
    private static final Logger logger = Logger.getLogger(OrderFoodDaoImpl.class);

    private static final String SQL_CREATE = "INSERT INTO orders_foods (order_id, food_id) values (?,?)";
    private static final String SQL_READ_BY_ID = "SELECT order_id, food_id FROM orders_foods WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE orders_foods SET order_id = ?, food_id = ? WHERE id = ?";
    private static final String SQL_READ_ALL = "SELECT id, order_id, food_id FROM orders_foods";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM orders_foods WHERE id = ?";
    private static final String SQL_READ_BY_ORDER_ID = "SELECT id, food_id FROM orders_foods WHERE order_id = ?";
    private static final String SQL_DELETE_BY_ORDER_ID = "DELETE FROM orders_foods WHERE order_id = ?";


    @Override
    public Integer create(OrderFood entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, entity.getOrderId().getId());
            statement.setInt(2, entity.getFoodId().getId());
            statement.executeUpdate();
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
    public OrderFood read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            OrderFood orderFood = new OrderFood();
            statement = connection.prepareStatement(SQL_READ_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orderFood.setId(id);
                orderFood.setOrderId(new Order(rs.getInt("order_id")));
                orderFood.setFoodId(new Food(rs.getInt("food_id")));
            }
            return orderFood;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void update(OrderFood entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(3, entity.getId());
            statement.setInt(1, entity.getOrderId().getId());
            statement.setInt(2, entity.getFoodId().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<OrderFood> read() throws DaoException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_READ_ALL);
            List<OrderFood> orderFoods = new ArrayList<>();
            while (rs.next()) {
                OrderFood orderFood = new OrderFood();
                orderFood.setId(rs.getInt("id"));
                orderFood.setFoodId(new Food(rs.getInt("food_id")));
                orderFood.setOrderId(new Order(rs.getInt("order_id")));
                orderFoods.add(orderFood);
            }
            return orderFoods;
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
    public List<OrderFood> readByOrderId(Integer orderId) throws DaoException {
        PreparedStatement statement = null;
        try {
            logger.info("readByOrderId in dao start");
            statement = connection.prepareStatement(SQL_READ_BY_ORDER_ID);
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            List<OrderFood> ordersFoods = new ArrayList<>();
            while (rs.next()) {
                OrderFood orderFood = new OrderFood();
                orderFood.setFoodId(new Food(rs.getInt("food_id")));
                orderFood.setId(rs.getInt("id"));
                orderFood.setOrderId(new Order(orderId));
                ordersFoods.add(orderFood);
            }
            return ordersFoods;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByOrderId(Integer orderId) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_BY_ORDER_ID);
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
