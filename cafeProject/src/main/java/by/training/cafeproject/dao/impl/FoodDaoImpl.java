package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.FoodDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl extends BaseDaoImpl implements FoodDao {

    @Override
    public void create(Food entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO foods (id, title, description, price, type, recipe) VALUES (?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getTitle());
            statement.setString(3, entity.getDescription());
            statement.setDouble(4, entity.getPrice());
            statement.setInt(5, entity.getTypeNumber());
            statement.setString(6, entity.getRecipe());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public Food read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT title, description, price, type, recipe FROM foods WHERE id = ?";
            Food food = new Food();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                food.setId(id);
                food.setTitle(rs.getString("title"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setType(rs.getInt("type"));
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
            String sql = "UPDATE foods SET title = ?, description = ?, price = ?, type = ?, recipe = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(5, entity.getId());
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getDescription());
            statement.setDouble(3, entity.getPrice());
            statement.setInt(4, entity.getTypeNumber());
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
            String sql = "SELECT id, title, description, price, type, recipe FROM foods";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Food> foods = new ArrayList<>();
            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getInt("id"));
                food.setTitle(rs.getString("title"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setType(rs.getInt("type"));
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
    public void delete(Food entity) throws DaoException {
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
    public List<Food> readByTitle(String search) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, description, price, type, recipe FROM foods WHERE title = ?";
            List<Food> foods = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setTitle(search);
                food.setId(rs.getInt("id"));
                food.setDescription(rs.getString("description"));
                food.setPrice(rs.getDouble("price"));
                food.setType(rs.getInt("type"));
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
    public List<Food> readByPrice(Double search) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, title, description, type, recipe FROM foods WHERE price = ?";
            List<Food> foods = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setPrice(search);
                food.setTitle(rs.getString("title"));
                food.setId(rs.getInt("id"));
                food.setDescription(rs.getString("description"));
                food.setType(rs.getInt("type"));
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
    public List<Food> readByType(Integer typeNumber) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, title, description, price, recipe FROM foods WHERE type = ?";
            List<Food> foods = new ArrayList<>();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, typeNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setType(typeNumber);
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
            String sql = "DELETE FROM foods WHERE title = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByType(Integer typeNumber) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM foods WHERE type = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, typeNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
