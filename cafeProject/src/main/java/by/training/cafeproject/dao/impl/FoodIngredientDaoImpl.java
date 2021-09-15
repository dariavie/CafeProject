package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.FoodIngredientDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.domain.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodIngredientDaoImpl extends BaseDaoImpl implements FoodIngredientDao {

    @Override
    public void create(FoodIngredient entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO foods_ingredients (id, food_id, ingredient_id, ingredient_amount) VALUES (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setInt(2, entity.getFoodId().getId());
            statement.setInt(3, entity.getIngredientId().getId());
            statement.setString(4, entity.getIngredientAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public FoodIngredient read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT food_id, ingredient_id, ingredient_amount FROM foods_ingredients WHERE id = ?";
            FoodIngredient foodIngredient = new FoodIngredient();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                foodIngredient.setId(id);
                foodIngredient.setFoodId(new Food(rs.getInt("food_id")));
                foodIngredient.setIngredientId(new Ingredient(rs.getInt("ingredient_id")));
                foodIngredient.setIngredientAmount(rs.getString("ingredient_amount"));
            }
            return foodIngredient;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void update(FoodIngredient entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE foods_ingredients SET food_id = ?, ingredient_id = ?, ingredient_amount = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(4, entity.getId());
            statement.setInt(1, entity.getFoodId().getId());
            statement.setInt(2, entity.getIngredientId().getId());
            statement.setString(3, entity.getIngredientAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<FoodIngredient> read() throws DaoException {
        Statement statement = null;
        try {
            String sql = "SELECT id, food_id, ingredient_id, ingredient_amount FROM foods_ingredients";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<FoodIngredient> foodsIngredients = new ArrayList<>();
            while (rs.next()) {
                FoodIngredient foodIngredient = new FoodIngredient();
                foodIngredient.setId(rs.getInt("id"));
                foodIngredient.setFoodId(new Food(rs.getInt("food_id")));
                foodIngredient.setIngredientId(new Ingredient(rs.getInt("ingredient_id")));
                foodIngredient.setIngredientAmount(rs.getString("ingredient_amount"));
                foodsIngredients.add(foodIngredient);
            }
            return foodsIngredients;
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
            String sql = "DELETE FROM foods_ingredients WHERE id = ?";
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
    public void delete(FoodIngredient entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM foods_ingredients WHERE id = ?";
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
    public void deleteByFoodId(Integer foodId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM foods_ingredients WHERE food_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, foodId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public void deleteByIngredientId(Integer ingredientId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM foods_ingredients WHERE ingredient_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, ingredientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<FoodIngredient> readByFoodId(Integer foodId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, ingredient_id, ingredient_amount FROM foods_ingredients WHERE food_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, foodId);
            ResultSet rs = statement.executeQuery();
            List<FoodIngredient> foodsIngredients = new ArrayList<>();
            while (rs.next()) {
                FoodIngredient foodIngredient = new FoodIngredient();
                foodIngredient.setFoodId(new Food(foodId));
                foodIngredient.setId(rs.getInt("id"));
                foodIngredient.setIngredientId(new Ingredient(rs.getInt("ingredient_id")));
                foodIngredient.setIngredientAmount(rs.getString("ingredient_amount"));
                foodsIngredients.add(foodIngredient);
            }
            return foodsIngredients;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }

    @Override
    public List<FoodIngredient> readByIngredientId(Integer ingredientId) throws DaoException {
        PreparedStatement statement = null;
        try {
            String sql = "SELECT id, food_id, ingredient_amount FROM foods_ingredients WHERE ingredient_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, ingredientId);
            ResultSet rs = statement.executeQuery();
            List<FoodIngredient> foodsIngredients = new ArrayList<>();
            while (rs.next()) {
                FoodIngredient foodIngredient = new FoodIngredient();
                foodIngredient.setIngredientId(new Ingredient(ingredientId));
                foodIngredient.setId(rs.getInt("id"));
                foodIngredient.setFoodId(new Food(rs.getInt("food_id")));
                foodIngredient.setIngredientAmount(rs.getString("ingredient_amount"));
                foodsIngredients.add(foodIngredient);
            }
            return foodsIngredients;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            this.closePreparedStatement(statement);
        }
    }
}
