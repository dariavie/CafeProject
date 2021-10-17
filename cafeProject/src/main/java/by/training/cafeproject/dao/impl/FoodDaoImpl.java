package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.FoodDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodType;
import by.training.cafeproject.domain.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl extends BaseDaoImpl implements FoodDao {
    private static final Logger logger = Logger.getLogger(FoodDaoImpl.class);

    private static final String SQL_CREATE = "INSERT INTO foods (title, description, price, type, recipe) VALUES (?,?,?,?,?)";
    private static final String SQL_READ_BY_ID = "SELECT title, description, price, type, recipe FROM foods WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE foods SET title = ?, description = ?, price = ?, type = ?, recipe = ? WHERE id = ?";
    private static final String SQL_READ_ALL = "SELECT id, title, description, price, type, recipe FROM foods";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM foods WHERE id = ?";
    private static final String SQL_READ_BY_TITLE = "SELECT id, description, price, type, recipe FROM foods WHERE title = ?";
    private static final String SQL_READ_BY_TYPE = "SELECT id, title, description, price, recipe FROM foods WHERE type = ?";
    private static final String SQL_DELETE_BY_TITLE = "DELETE FROM foods WHERE title = ?";

    @Override
    public Integer create(Food entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getDescription());
            statement.setDouble(3, entity.getPrice());
            statement.setInt(4, entity.getType().getId());
            statement.setString(5, entity.getRecipe());
            statement.executeUpdate();
            logger.info("statement was executed");
            ResultSet rs = statement.executeQuery();
            logger.info("rs start");
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
    public Food read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            Food food = new Food();
            statement = connection.prepareStatement(SQL_READ_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                food.setId(id);
                food.setTitle(rs.getString("title"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setType(FoodType.getById(rs.getInt("type")));
                food.setRecipe(rs.getString("recipe"));
            }
            return food;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void update(Food entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(5, entity.getId());
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getDescription());
            statement.setDouble(3, entity.getPrice());
            statement.setInt(4, entity.getType().getId());
            statement.setString(5, entity.getRecipe());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<Food> read() throws DaoException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_READ_ALL);
            List<Food> foods = new ArrayList<>();
            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getInt("id"));
                food.setTitle(rs.getString("title"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setType(FoodType.getById(rs.getInt("type")));
                food.setRecipe(rs.getString("recipe"));
                foods.add(food);
            }
            return foods;
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
    public Food readByTitle(String search) throws DaoException {
        PreparedStatement statement = null;
        try {
            Food food = new Food();
            statement = connection.prepareStatement(SQL_READ_BY_TITLE);
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                food.setTitle(search);
                food.setId(rs.getInt("id"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setType(FoodType.getById(rs.getInt("type")));
                food.setRecipe(rs.getString("recipe"));
            }
            return food;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<Food> readByType(FoodType type) throws DaoException {
        PreparedStatement statement = null;
        try {
            List<Food> foods = new ArrayList<>();
            statement = connection.prepareStatement(SQL_READ_BY_TYPE);
            statement.setInt(1, type.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setType(type);
                food.setTitle(rs.getString("title"));
                food.setId(rs.getInt("id"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setRecipe(rs.getString("recipe"));
                foods.add(food);
            }
            return foods;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByTitle(String title) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_BY_TITLE);
            statement.setString(1, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
