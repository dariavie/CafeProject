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
    private static final String SQL_CREATE = "INSERT INTO foods_ingredients (food_id, ingredient_id, ingredient_amount) VALUES (?,?,?)";
    private static final String SQL_READ_BY_ID = "SELECT food_id, ingredient_id, ingredient_amount FROM foods_ingredients WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE foods_ingredients SET food_id = ?, ingredient_id = ?, ingredient_amount = ? WHERE id = ?";
    private static final String SQL_READ_ALL = "SELECT id, food_id, ingredient_id, ingredient_amount FROM foods_ingredients";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM foods_ingredients WHERE id = ?";
    private static final String SQL_DELETE_BY_FOOD_ID = "DELETE FROM foods_ingredients WHERE food_id = ?";
    private static final String SQL_DELETE_BY_INGREDIENT_ID = "DELETE FROM foods_ingredients WHERE ingredient_id = ?";
    private static final String SQL_READ_BY_FOOD_ID = "SELECT id, ingredient_id, ingredient_amount FROM foods_ingredients WHERE food_id = ?";
    private static final String SQL_READ_BY_INGREDIENT_ID = "SELECT id, food_id, ingredient_amount FROM foods_ingredients WHERE ingredient_id = ?";

    @Override
    public Integer create(FoodIngredient entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE);
            statement.setInt(1, entity.getFoodId().getId());
            statement.setInt(2, entity.getIngredientId().getId());
            statement.setString(3, entity.getIngredientAmount());
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
    public FoodIngredient read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            FoodIngredient foodIngredient = new FoodIngredient();
            statement = connection.prepareStatement(SQL_READ_BY_ID);
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
            statement = connection.prepareStatement(SQL_UPDATE);
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
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_READ_ALL);
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
    public void deleteByFoodId(Integer foodId) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_BY_FOOD_ID);
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
            statement = connection.prepareStatement(SQL_DELETE_BY_INGREDIENT_ID);
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
            statement = connection.prepareStatement(SQL_READ_BY_FOOD_ID);
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
            statement = connection.prepareStatement(SQL_READ_BY_INGREDIENT_ID);
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
